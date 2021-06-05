package io.swagger.api;

import io.swagger.entities.AttractionEntity;
import io.swagger.entities.QueueEntity;
import io.swagger.entities.ReservationEntity;
import io.swagger.entities.ScheduleEntity;
import io.swagger.model.ErrorResponse;
import io.swagger.model.Queue;
import io.swagger.model.Schedule;
import io.swagger.model.Schedules;
import io.swagger.model.UpcomingSchedule;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repositories.QueueRepository;
import io.swagger.repositories.ReservationRepository;
import io.swagger.repositories.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")

@Controller
public class SchedulesApiController implements SchedulesApi {

    private static final Logger log = LoggerFactory.getLogger(SchedulesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public SchedulesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity createSchedule(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Schedule body) {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.getSchedules(new AttractionEntity().setId(body.getAttraction()));
        ScheduleEntity entity = ScheduleEntity.fromModel(body);
        for (ScheduleEntity scheduleEntity : scheduleEntities) {
            if (isBetween(entity.getStartTime(), scheduleEntity.getStartTime(), scheduleEntity.getEndTime()) ||
                    isBetween(entity.getEndTime(), scheduleEntity.getStartTime(), scheduleEntity.getEndTime())) {
                return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorCode("401").errorMessage("Time conflict between schedules.").success(false));
            }
        }

        scheduleRepository.save(entity);
        Schedules schedules = new Schedules();
        schedules.addAll(scheduleRepository.getSchedules(new AttractionEntity().setId(body.getAttraction())).stream().map(ScheduleEntity::toModel).collect(Collectors.toList()));
        return new ResponseEntity<Schedules>(HttpStatus.OK).ok(schedules);
    }

    public ResponseEntity deleteSchedule(@ApiParam(value = "") @Valid @RequestParam(value = "scheduleId", required = false) String scheduleId) {
        ScheduleEntity scheduleEntity = scheduleRepository.findOne(scheduleId);
        Schedules schedules = new Schedules();
        if (scheduleEntity != null) {
            String attractionId = scheduleEntity.getAttraction().getId();
            List<ReservationEntity> reservationEntities = reservationRepository.getReservationsFromSchedule(scheduleEntity);
            try {
                for (ReservationEntity entity : reservationEntities) {
                    reservationRepository.delete(entity.getId());
                }
                scheduleRepository.delete(scheduleId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorCode("401").errorMessage("Cannot delete: there are reservations booked for this schedule.").success(false));
            }
            schedules.addAll(scheduleRepository.getSchedules(new AttractionEntity().setId(attractionId)).stream().map(ScheduleEntity::toModel).collect(Collectors.toList()));
        }
        return new ResponseEntity<Schedules>(HttpStatus.OK).ok(schedules);
    }

    public ResponseEntity<Schedules> getSchedules(@ApiParam(value = "") @Valid @RequestParam(value = "attractionId", required = false) String attractionId) {
        Schedules schedules = new Schedules();
        schedules.addAll(scheduleRepository.getSchedules(new AttractionEntity().setId(attractionId)).stream().map(ScheduleEntity::toModel).collect(Collectors.toList()));
        return new ResponseEntity<Schedules>(HttpStatus.OK).ok(schedules);
    }

    public ResponseEntity<UpcomingSchedule> getUpcomingSchedule(@ApiParam(value = "") @Valid @RequestParam(value = "attractionId", required = false) String attractionId) {
        List<QueueEntity> queueEntities = queueRepository.getQueuesOfAttraction(new AttractionEntity().setId(attractionId));
        int nextNumber = (queueEntities.size() == 0) ? 0 : queueEntities.get(0).getNumberInLine();
        ScheduleEntity entity = scheduleRepository.getUpcomingSchedule(new AttractionEntity().setId(attractionId));
        if (entity != null) {
            if (entity.getAvailableCapacity() > 0 && entity.getStatus().equals(ScheduleEntity.Status.postCheckin)) {
                queueRepository.save(queueEntities.get(0).setStatus(QueueEntity.Status.called));
            }
            return new ResponseEntity<UpcomingSchedule>(HttpStatus.OK).ok(new UpcomingSchedule(ScheduleEntity.toModel(entity), nextNumber));
        } else {
            return ResponseEntity.ok(null);
        }
    }

    public ResponseEntity<Schedules> updateSchedule(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Schedule body) {
        ScheduleEntity scheduleToUpdate = scheduleRepository.findOne(body.getId());
        scheduleToUpdate.setStatus(body.getStatus()!= null ? ScheduleEntity.Status.fromValue(body.getStatus().toString()) : scheduleToUpdate.getStatus());
        if (scheduleToUpdate.getStatus().equals(ScheduleEntity.Status.checkingIn)) {
            for (ReservationEntity reservationEntity :reservationRepository.getReservationsFromSchedule(scheduleToUpdate)) {
                reservationRepository.save(reservationEntity.setStatus(ReservationEntity.Status.checkingIn));
            }
        }
        if (scheduleToUpdate.getStatus().equals(ScheduleEntity.Status.postCheckin) || scheduleToUpdate.getStatus().equals(ScheduleEntity.Status.completed)) {
            for (ReservationEntity reservationEntity :reservationRepository.getReservationsFromSchedule(scheduleToUpdate)) {
                reservationRepository.save(reservationEntity.setStatus(ReservationEntity.Status.passed));
            }
        }
        if (scheduleToUpdate.getStatus().equals(ScheduleEntity.Status.cancelled)) {
            for (ReservationEntity reservationEntity :reservationRepository.getReservationsFromSchedule(scheduleToUpdate)) {
                reservationRepository.save(reservationEntity.setStatus(ReservationEntity.Status.cancelled));
            }
        }
        scheduleToUpdate.setAvailableCapacity(body.getAvailableSeats()!= null ? body.getAvailableSeats() : scheduleToUpdate.getAvailableCapacity());
        scheduleRepository.save(scheduleToUpdate);
        Schedules schedules = new Schedules();
        schedules.addAll(scheduleRepository.getSchedules(new AttractionEntity().setId(scheduleToUpdate.getAttraction().getId())).stream().map(ScheduleEntity::toModel).collect(Collectors.toList()));
        return new ResponseEntity<Schedules>(HttpStatus.OK).ok(schedules);
    }

    public ResponseEntity updateUpcomingSchedule(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UpcomingSchedule body) {
        ScheduleEntity scheduleToUpdate = scheduleRepository.findOne(body.getId());
        int nextNumber = 0;
        if (scheduleToUpdate == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorCode("403").errorMessage("Cannot find the schedule to update.").success(false));
        }
        String attractionId = scheduleToUpdate.getAttraction().getId();
        scheduleToUpdate.setStatus(body.getStatus()!= null ? ScheduleEntity.Status.fromValue(body.getStatus().toString()) : scheduleToUpdate.getStatus());
        if (scheduleToUpdate.getStatus().equals(ScheduleEntity.Status.checkingIn)) {
            for (ReservationEntity reservationEntity :reservationRepository.getReservationsFromSchedule(scheduleToUpdate)) {
                reservationRepository.save(reservationEntity.setStatus(ReservationEntity.Status.checkingIn));
            }
        }
        if (scheduleToUpdate.getStatus().equals(ScheduleEntity.Status.postCheckin)) {
            int seatsToRelease = 0;
            for (ReservationEntity reservationEntity :reservationRepository.getReservationsFromSchedule(scheduleToUpdate)) {
                if (reservationEntity.getStatus().equals(ReservationEntity.Status.checkingIn)) {
                    seatsToRelease ++;
                    reservationRepository.save(reservationEntity.setStatus(ReservationEntity.Status.passed));
                }
            }
            scheduleToUpdate.setAvailableCapacity(scheduleToUpdate.getAvailableCapacity() + seatsToRelease);
            if (scheduleToUpdate.getAvailableCapacity() > 0 && body.getNextNumberToCall() == null) {
                List<QueueEntity> queueEntities = queueRepository.getQueuesOfAttraction(new AttractionEntity().setId(attractionId));
                if (queueEntities.size() > 0) {
                    queueRepository.save(queueEntities.get(0).setStatus(QueueEntity.Status.called));
                }
            }
            // Find next number from queue
            List<QueueEntity> queueEntities = queueRepository.getQueuesOfAttraction(new AttractionEntity().setId(attractionId));
            nextNumber = (queueEntities.size() == 0) ? 0 : queueEntities.get(0).getNumberInLine();
            if (body.getNextNumberToCall() != null) {
                for (QueueEntity entity : queueEntities) {
                    if (entity.getNumberInLine() < body.getNextNumberToCall()) {
                        entity.setStatus(QueueEntity.Status.passed);
                        queueRepository.save(entity);
                    } else {
                        nextNumber = entity.getNumberInLine();
                        entity.setStatus(QueueEntity.Status.called);
                        queueRepository.save(entity);
                        break;
                    }
                    nextNumber = 0;
                }
            }
        }
        if (scheduleToUpdate.getStatus().equals(ScheduleEntity.Status.cancelled)) {
            for (ReservationEntity reservationEntity :reservationRepository.getReservationsFromSchedule(scheduleToUpdate)) {
                reservationRepository.save(reservationEntity.setStatus(ReservationEntity.Status.cancelled));
            }
        }
        if (scheduleToUpdate.getStatus().equals(ScheduleEntity.Status.completed)) {
            for (ReservationEntity reservationEntity :reservationRepository.getReservationsFromSchedule(scheduleToUpdate)) {
                reservationRepository.save(reservationEntity.setStatus(ReservationEntity.Status.completed));
            }
            for (QueueEntity queueEntity : queueRepository.getQueuesOfAttraction(new AttractionEntity().setId(attractionId))) {
                if (queueEntity.getStatus().equals(QueueEntity.Status.called)) {
                    queueRepository.save(queueEntity.setStatus(QueueEntity.Status.waiting));
                }
            }
        }
        if (body.getAvailableSeats() != null && body.getStatus() == null) {
            scheduleToUpdate.setAvailableCapacity(body.getAvailableSeats());
        }
        scheduleRepository.save(scheduleToUpdate);
        ScheduleEntity returnSchedule = scheduleRepository.getUpcomingSchedule(new AttractionEntity().setId(attractionId));
        if (returnSchedule != null) {
            return new ResponseEntity<UpcomingSchedule>(HttpStatus.OK).ok(new UpcomingSchedule(ScheduleEntity.toModel(returnSchedule), nextNumber));
        } else {
            return new ResponseEntity<UpcomingSchedule>(HttpStatus.OK).ok(null);
        }
    }

    public static boolean isBetween(String targetStr, String start, String end) {
        LocalTime target = LocalTime.parse(targetStr + ":00" ) ;
        if (targetStr.equals(start) || targetStr.equals(end)) {
            return true;
        }
        return (
                target.isAfter( LocalTime.parse( start + ":00") )
                        &&
                        target.isBefore( LocalTime.parse( end + ":00") )
        );
    }
}
