package io.swagger.api;

import io.swagger.entities.GroupEntity;
import io.swagger.entities.GuestEntity;
import io.swagger.entities.OperatorEntity;
import io.swagger.entities.QueueEntity;
import io.swagger.model.CurrentUser;
import io.swagger.model.ErrorResponse;
import io.swagger.model.User;
import io.swagger.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repositories.GroupRepository;
import io.swagger.repositories.GuestRepository;
import io.swagger.repositories.OperatorRepository;
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
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity addUserToGroup(@NotNull @ApiParam(value = "user account ID that owns the group", required = true) @Valid @RequestParam(value = "userId", required = true) String userId,@ApiParam(value = "user that needs to add to group" ,required=true )  @Valid @RequestBody User body) {
        GuestEntity guestEntity = guestRepository.findOne(userId);
        GuestEntity guestEntityToAdd = guestRepository.findUserByEmail(body.getEmail());
        if (guestEntity == null || guestEntityToAdd == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().success(false).errorCode("401").errorMessage("Non-existed user."));
        }
        groupRepository.save(new GroupEntity().setOwner(guestEntity).setMember(guestEntityToAdd));
        Users responseUsers = new Users();
        for ( GroupEntity entity : groupRepository.getGroupByUser(new GuestEntity().setId(userId))) {
            responseUsers.add(GuestEntity.toModel(guestRepository.findOne(entity.getMember().getId())));
        }
        return ResponseEntity.ok(responseUsers);

    }

    public ResponseEntity<Void> deletUserFromGroup(@NotNull @ApiParam(value = "user account ID that owns the group", required = true) @Valid @RequestParam(value = "userId", required = true) String userId,@NotNull @ApiParam(value = "The user that needs to be deleted from the group", required = true) @Valid @RequestParam(value = "userToDelete", required = true) String userToDelete) {
        GroupEntity entity = groupRepository.getGroupByUser(new GuestEntity().setId(userId), new GuestEntity().setId(userToDelete));
        if (entity != null) {
            groupRepository.delete(entity);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteUser(@NotNull @ApiParam(value = "The name that needs to be deleted", required = true) @Valid @RequestParam(value = "userId", required = true) String userId) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity getUserByName(@NotNull @ApiParam(value = "The name that needs to be fetched. Use user1 for testing. ", required = true) @Valid @RequestParam(value = "userId", required = true) String userId) {
        OperatorEntity operatorEntity = operatorRepository.findOne(userId);
        if (operatorEntity != null) {
            return ResponseEntity.ok(OperatorEntity.toModel(operatorEntity).access("admin"));
        } else {
            GuestEntity guestEntity = guestRepository.findOne(userId);
            if (guestEntity != null) {
                return ResponseEntity.ok(GuestEntity.toCurrentUser(guestEntity).access("user"));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().success(false).errorCode("401").errorMessage("Non-existed userId."));
    }

    public ResponseEntity<Users> getUserGroup(@NotNull @ApiParam(value = "user account ID that owns the group", required = true) @Valid @RequestParam(value = "userId", required = true) String userId) {
        Users responseUsers = new Users();
        for ( GroupEntity entity : groupRepository.getGroupByUser(new GuestEntity().setId(userId))) {
            responseUsers.add(GuestEntity.toModel(guestRepository.findOne(entity.getMember().getId())));
        }
        return ResponseEntity.ok(responseUsers);
    }

    public ResponseEntity updateUser(@NotNull @ApiParam(value = "name that need to be updated", required = true) @Valid @RequestParam(value = "userId", required = true) String userId,@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody User body) {
        GuestEntity guestEntity = guestRepository.findOne(userId);
        if (guestEntity == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().success(false).errorCode("401").errorMessage("Non-existed user."));
        } else {
            if (body.getPassword() != null) {
                guestEntity.setPassword(body.getPassword());
            } else {
                guestEntity
                        .setEmail(body.getEmail() == null? guestEntity.getEmail() : body.getEmail())
                        .setName(body.getFirstName() + " " + body.getLastName())
                        .setPhone(body.getPhone() == null ? guestEntity.getPhone() : body.getPhone());
            }
            guestEntity = guestRepository.save(guestEntity);
            return ResponseEntity.ok(GuestEntity.toModel(guestEntity));
        }
    }

    public ResponseEntity createUser(@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody User body) {
        if (body.getEmail() == null || body.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().success(false).errorCode("401").errorMessage("Email and password are necessary."));
        }
        GuestEntity guestEntity = guestRepository.findUserByEmail(body.getEmail());
        if (guestEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse().success(false).errorCode("401").errorMessage("User email already exists."));
        } else {
            guestEntity = new GuestEntity()
                    .setName(body.getFirstName() + " " + body.getLastName())
                    .setPhone(body.getPhone() == null ? "" : body.getPhone())
                    .setPassword(body.getPassword())
                    .setEmail(body.getEmail());
            guestEntity = guestRepository.save(guestEntity);
            return ResponseEntity.ok(GuestEntity.toModel(guestEntity));
        }
    }
}
