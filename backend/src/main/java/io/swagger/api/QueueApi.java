/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ErrorResponse;
import io.swagger.model.JoinQueueRequest;
import io.swagger.model.Queue;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")

@Validated
@Api(value = "queue", description = "the queue API")
@RequestMapping(value = "")
public interface QueueApi {

    @ApiOperation(value = "", nickname = "getCurrentQueue", notes = "", response = Queue.class, tags={ "queue", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Queue.class) })
    @RequestMapping(value = "/queue",
        method = RequestMethod.GET)
    ResponseEntity<Queue> getCurrentQueue(@ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) String userId);

    @ApiOperation(value = "", nickname = "getCurrentQueue", notes = "", response = Queue.class, tags={ "queue", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Queue.class) })
    @RequestMapping(value = "/queue/count",
            method = RequestMethod.GET)
    ResponseEntity<Queue> getCurrentQueueSize(@ApiParam(value = "") @Valid @RequestParam(value = "attractionId", required = false) String attractionId);


    @ApiOperation(value = "", nickname = "joinNewQueue", notes = "join a queue", response = Queue.class, tags={ "queue", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Queue.class),
        @ApiResponse(code = 401, message = "Error", response = ErrorResponse.class) })
    @RequestMapping(value = "/queue/join",
        method = RequestMethod.POST)
    ResponseEntity<Queue> joinNewQueue(@ApiParam(value = "queue info" ,required=true )  @Valid @RequestBody JoinQueueRequest body);


    @ApiOperation(value = "", nickname = "quitQueue", notes = "quit from a queue", response = Queue.class, tags={ "queue", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Queue.class),
        @ApiResponse(code = 401, message = "Error", response = ErrorResponse.class) })
    @RequestMapping(value = "/queue/quit",
        method = RequestMethod.DELETE)
    ResponseEntity<Queue> quitQueue(@ApiParam(value = "user ID who wants to quit his/her queue.") @Valid @RequestParam(value = "user", required = false) String user);

}
