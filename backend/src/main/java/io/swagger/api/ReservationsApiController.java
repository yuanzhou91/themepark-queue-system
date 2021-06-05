package io.swagger.api;

import io.swagger.entities.AttractionEntity;
import io.swagger.entities.GuestEntity;
import io.swagger.entities.ReservationEntity;
import io.swagger.entities.ScheduleEntity;
import io.swagger.model.ErrorResponse;
import io.swagger.model.Reservation;
import io.swagger.model.Reservations;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repositories.AttractionRepository;
import io.swagger.repositories.GuestRepository;
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
public class ReservationsApiController implements ReservationsApi {

    private static final Logger log = LoggerFactory.getLogger(ReservationsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public ReservationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity confirmReservation(@ApiParam(value = "id of reservation to confirm") @Valid @RequestParam(value = "reservationId", required = false) String reservationId) {
        ReservationEntity reservationEntity = reservationRepository.findOne(reservationId);
        if (reservationEntity == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("No such reservation to confirm.").errorCode("401").success(false));
        }
        if (reservationEntity.getStatus().equals(ReservationEntity.Status.checkingIn)) {
            reservationEntity.setStatus(ReservationEntity.Status.confirmed);
            reservationEntity = reservationRepository.save(reservationEntity);
        }
        AttractionEntity attractionEntity = attractionRepository.findOne(reservationEntity.getAttraction().getId());
        ScheduleEntity scheduleEntity = scheduleRepository.findOne(reservationEntity.getSchedule().getId());
        return getReservations(reservationEntity.getGuest().getId());
    }

    public ResponseEntity createReservation(@ApiParam(value = "reservation" ,required=true )  @Valid @RequestBody Reservations body) {
        String mainUserId = body.get(0).getUser();
        for (Reservation reservation : body) {
            ReservationEntity reservationEntity = reservationRepository.getReservationsByUserAndAttraction(new GuestEntity().setId(reservation.getUser()), new AttractionEntity().setId(reservation.getAttraction()));
            if (reservationEntity != null) {
                return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("There are users who already reserved the attraction.").errorCode("401").success(false));
            }
            ScheduleEntity scheduleEntityToReserve = scheduleRepository.findOne(reservation.getSchedule());
            List<ReservationEntity> userReservations = reservationRepository.getReservationsByUser(new GuestEntity().setId(reservation.getUser()));
            for (ReservationEntity userReservation: userReservations) {
                ScheduleEntity schedule = scheduleRepository.findOne(userReservation.getSchedule().getId());
                if (isBetween(scheduleEntityToReserve.getStartTime(), schedule.getStartTime(), schedule.getEndTime())
                || isBetween(scheduleEntityToReserve.getEndTime(), schedule.getStartTime(), schedule.getEndTime())) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("There are users who has schedule conflicts.").errorCode("401").success(false));
                }
            }
            ScheduleEntity scheduleEntity = scheduleRepository.findOne(reservation.getSchedule());
            if (scheduleEntity == null) {
                return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("No such schedule to reserve.").errorCode("401").success(false));
            }
            if (!scheduleEntity.getStatus().equalsName("upcoming")) {
                return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("Schedule status is not able to reserve.").errorCode("401").success(false));
            }
            if (scheduleEntity.getAvailableCapacity() - scheduleEntity.getReservedForQueue() < body.size()) {
                return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("Schedule has not enough seats: only " + (scheduleEntity.getAvailableCapacity() - scheduleEntity.getReservedForQueue() ) + " is available").errorCode("401").success(false));
            }
        }
        for (Reservation reservation: body) {
            ScheduleEntity scheduleEntity = scheduleRepository.findOne(reservation.getSchedule());
            ReservationEntity reservationEntity =  new ReservationEntity()
                    .setAttraction(new AttractionEntity().setId(reservation.getAttraction()))
                    .setGuest(new GuestEntity().setId(reservation.getUser()))
                    .setSchedule(scheduleEntity)
                    .setStatus(ReservationEntity.Status.waiting);
            reservationRepository.save(reservationEntity);
            scheduleRepository.save(scheduleEntity.setAvailableCapacity(scheduleEntity.getAvailableCapacity() - 1));
        }

        List<ReservationEntity> reservationEntities = reservationRepository.getReservationsByUser(new GuestEntity().setId(mainUserId));
        Reservations reservations = new Reservations();
        for (ReservationEntity reservationEntity : reservationEntities) {
            AttractionEntity attractionEntity = attractionRepository.findOne(reservationEntity.getAttraction().getId());
            ScheduleEntity scheduleEntity = scheduleRepository.findOne(reservationEntity.getSchedule().getId());
            reservations.add(ReservationEntity.toModel(reservationEntity, attractionEntity, scheduleEntity));
        }
        return ResponseEntity.ok(reservations);
    }

    public ResponseEntity<Reservations> deleteReservation(@NotNull @ApiParam(value = "id of reservation to delete", required = true) @Valid @RequestParam(value = "reservationId", required = true) String reservationId) {
        ReservationEntity reservationEntity = reservationRepository.findOne(reservationId);
        Reservations reservations = new Reservations();
        if (reservationEntity != null) {
            reservationRepository.delete(reservationId);
            ScheduleEntity scheduleEntity = scheduleRepository.findOne(reservationEntity.getSchedule().getId());
            scheduleRepository.save(scheduleEntity.setAvailableCapacity(scheduleEntity.getAvailableCapacity() + 1));

            List<ReservationEntity> reservationEntities = reservationRepository.getReservationsByUser(reservationEntity.getGuest());
            for (ReservationEntity entity : reservationEntities) {
                AttractionEntity attractionEntity = attractionRepository.findOne(entity.getAttraction().getId());
                ScheduleEntity schedule = scheduleRepository.findOne(entity.getSchedule().getId());
                reservations.add(ReservationEntity.toModel(entity, attractionEntity, schedule));
            }
        }
        return ResponseEntity.ok(reservations);
    }

    public ResponseEntity getReservations(@ApiParam(value = "user account") @Valid @RequestParam(value = "userId", required = false) String userId) {
        GuestEntity guestEntity = guestRepository.findOne(userId);
        if (guestEntity == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().success(false).errorCode("401").errorMessage("Non-existed user."));
        }
        List<ReservationEntity> reservationEntities = reservationRepository.getReservationsByUser(guestEntity);
        Reservations reservations = new Reservations();
        for (ReservationEntity reservationEntity : reservationEntities) {
            AttractionEntity attractionEntity = attractionRepository.findOne(reservationEntity.getAttraction().getId());
            ScheduleEntity scheduleEntity = scheduleRepository.findOne(reservationEntity.getSchedule().getId());
            reservations.add(ReservationEntity.toModel(reservationEntity, attractionEntity, scheduleEntity));
        }
        return ResponseEntity.ok(reservations);
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
