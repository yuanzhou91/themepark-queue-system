/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ErrorResponse;
import io.swagger.model.Schedule;
import io.swagger.model.Schedules;
import io.swagger.model.UpcomingSchedule;
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
@Api(value = "schedules", description = "the schedules API")
@RequestMapping(value = "")
public interface SchedulesApi {

    @ApiOperation(value = "", nickname = "createSchedule", notes = "create a new schedule", response = Schedules.class, tags={ "schedule", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Schedules.class),
        @ApiResponse(code = 401, message = "Error", response = ErrorResponse.class) })
    @RequestMapping(value = "/schedules",
        method = RequestMethod.POST)
    ResponseEntity<Schedules> createSchedule(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Schedule body);


    @ApiOperation(value = "", nickname = "deleteSchedule", notes = "delete a schedule", response = Schedules.class, tags={ "schedule", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Schedules.class),
        @ApiResponse(code = 401, message = "Error", response = ErrorResponse.class) })
    @RequestMapping(value = "/schedules",
        method = RequestMethod.DELETE)
    ResponseEntity<Schedules> deleteSchedule(@ApiParam(value = "") @Valid @RequestParam(value = "scheduleId", required = false) String scheduleId);


    @ApiOperation(value = "", nickname = "getSchedules", notes = "", response = Schedules.class, tags={ "schedule", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Schedules.class) })
    @RequestMapping(value = "/schedules",
        method = RequestMethod.GET)
    ResponseEntity<Schedules> getSchedules(@ApiParam(value = "") @Valid @RequestParam(value = "attractionId", required = false) String attractionId);


    @ApiOperation(value = "", nickname = "getUpcomingSchedule", notes = "", response = UpcomingSchedule.class, tags={ "schedule", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = UpcomingSchedule.class) })
    @RequestMapping(value = "/schedules/upcomingSchedule",
        method = RequestMethod.GET)
    ResponseEntity<UpcomingSchedule> getUpcomingSchedule(@ApiParam(value = "") @Valid @RequestParam(value = "attractionId", required = false) String attractionId);


    @ApiOperation(value = "", nickname = "updateSchedule", notes = "update a schedule", response = Schedules.class, tags={ "schedule", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Schedules.class),
        @ApiResponse(code = 401, message = "Error", response = ErrorResponse.class) })
    @RequestMapping(value = "/schedules",
        method = RequestMethod.PUT)
    ResponseEntity<Schedules> updateSchedule(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Schedule body);


    @ApiOperation(value = "", nickname = "updateUpcomingSchedule", notes = "", response = UpcomingSchedule.class, tags={ "schedule", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = UpcomingSchedule.class) })
    @RequestMapping(value = "/schedules/upcomingSchedule",
        method = RequestMethod.PUT)
    ResponseEntity<UpcomingSchedule> updateUpcomingSchedule(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UpcomingSchedule body);

}
