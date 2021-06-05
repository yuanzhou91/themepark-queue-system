package io.swagger.api;


import io.swagger.entities.AttractionEntity;
import io.swagger.entities.GuestEntity;
import io.swagger.entities.QueueEntity;
import io.swagger.entities.ReservationEntity;
import io.swagger.model.Attraction;
import io.swagger.model.ErrorResponse;
import io.swagger.model.JoinQueueRequest;
import io.swagger.model.Queue;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repositories.AttractionRepository;
import io.swagger.repositories.QueueRepository;
import io.swagger.repositories.ReservationRepository;
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
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")

@Controller
public class QueueApiController implements QueueApi {

    private static final Logger log = LoggerFactory.getLogger(QueueApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public QueueApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity getCurrentQueue(@ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) String userId) {
        QueueEntity queueEntity = queueRepository.getQueueOfUser(new GuestEntity().setId(userId));
        if (queueEntity != null) {
            AttractionEntity attraction = attractionRepository.findOne(queueEntity.getAttraction().getId());
            Queue queue = new Queue()
                .id(String.valueOf(queueEntity.getId()))
                .attraction(attraction.getId())
                .location(attraction.getLocation())
                .user(queueEntity.getGuest().getId())
                .numberInQueue(queueEntity.getNumberInLine())
                .nextNumberToCall(queueRepository.getNextNumberToCall(attraction))
                .eta("N/A")
                .status(Queue.StatusEnum.fromValue(queueEntity.getStatus().toString()));
            return new ResponseEntity<Queue>(HttpStatus.OK).ok(queue);
        } else {
            return new ResponseEntity<Queue>(HttpStatus.OK).ok().build();
        }
    }

    public ResponseEntity joinNewQueue(@ApiParam(value = "queue info" ,required=true )  @Valid @RequestBody JoinQueueRequest body) {
        QueueEntity mainUserQueue = null;
        AttractionEntity attraction = attractionRepository.findOne(body.getAttraction());
        for (String userId : body.getUsers()) {
            QueueEntity queueEntity = queueRepository.getQueueOfUser(new GuestEntity().setId(userId));
            if (queueEntity != null) {
                if (queueEntity.getStatus().equalsName("called") || queueEntity.getStatus().equalsName("waiting")) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("There are users who already joined the queue.").errorCode("401").success(false));
                }
            }
            List<ReservationEntity> reservationEntities = reservationRepository.getReservationsByUser(new GuestEntity().setId(userId));
            for (ReservationEntity e : reservationEntities) {
                if (e.getAttraction().getId().equals(attraction.getId()) &&
                        (
                                e.getStatus().equals(ReservationEntity.Status.waiting)
                                        || e.getStatus().equals(ReservationEntity.Status.checkingIn)
                                        || e.getStatus().equals(ReservationEntity.Status.confirmed)
                        )
                ) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("Your group cannot join a queue because someone has reserved same attraction already.").errorCode("401").success(false));
                }
            }
        }
        for (String userId : body.getUsers()) {
            QueueEntity queueEntity = queueRepository.getQueueOfUser(new GuestEntity().setId(userId));
            if (queueEntity != null) {
                if (queueEntity.getStatus().equalsName("called") || queueEntity.getStatus().equalsName("waiting")) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("There are users who already joined the queue.").errorCode("401").success(false));
                } else {
                    queueRepository.delete(queueEntity);
                }
            }
            List<ReservationEntity> reservationEntities = reservationRepository.getReservationsByUser(new GuestEntity().setId(userId));
            for (ReservationEntity e : reservationEntities) {
                if (e.getAttraction().getId().equals(attraction.getId()) &&
                        (
                                e.getStatus().equals(ReservationEntity.Status.waiting)
                                        || e.getStatus().equals(ReservationEntity.Status.checkingIn)
                                        || e.getStatus().equals(ReservationEntity.Status.confirmed)
                        )
                ) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().errorMessage("You cannot join a queue because you have reserved same attraction .").errorCode("401").success(false));
                }
            }
            Integer lastNumberInQueue = queueRepository.getLargestNumberForQueue(new AttractionEntity().setId(body.getAttraction()));
            QueueEntity userQueueEntity = queueRepository.save(new QueueEntity()
                    .setAttraction(attraction)
                    .setGuest(new GuestEntity().setId(userId))
                    .setNumberInLine(lastNumberInQueue == null ? 1: lastNumberInQueue + 1)
                    .setStatus(QueueEntity.Status.waiting)
            );
            if (mainUserQueue == null) {
                mainUserQueue = userQueueEntity;
            }

        }
        return new ResponseEntity(HttpStatus.OK).ok(QueueEntity.toModel(mainUserQueue, queueRepository.getNextNumberToCall(attraction)));
    }

    public ResponseEntity quitQueue(@ApiParam(value = "user ID who wants to quit his/her queue.") @Valid @RequestParam(value = "user", required = false) String user) {
        QueueEntity queueEntity = queueRepository.getQueueOfUser(new GuestEntity().setId(user));
        if (queueEntity != null) {
            queueRepository.delete(queueEntity);
        }
        return new ResponseEntity(HttpStatus.OK).ok().build();
    }

    public ResponseEntity getCurrentQueueSize(@ApiParam(value = "") @Valid @RequestParam(value = "attractionId", required = false) String attractionId) {
        Integer count = queueRepository.getQueueSize(new AttractionEntity().setId(attractionId));
        return new ResponseEntity(HttpStatus.OK).ok(count);
    }
}
