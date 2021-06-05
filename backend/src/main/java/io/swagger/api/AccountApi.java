/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ErrorResponse;
import io.swagger.model.LoginParams;
import io.swagger.model.LoginResult;
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
@Api(value = "account", description = "the account API")
@RequestMapping(value = "")
public interface AccountApi {

    @ApiOperation(value = "", nickname = "login", notes = "log into the system", response = LoginResult.class, tags={ "login", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = LoginResult.class),
        @ApiResponse(code = 401, message = "Error", response = ErrorResponse.class) })
    @RequestMapping(value = "/account/login",
        method = RequestMethod.POST)
    ResponseEntity<LoginResult> login(@ApiParam(value = "login params" ,required=true )  @Valid @RequestBody LoginParams body);


    @ApiOperation(value = "", nickname = "logout", notes = "log out of the system", response = Object.class, tags={ "login", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Object.class),
        @ApiResponse(code = 401, message = "Error", response = ErrorResponse.class) })
    @RequestMapping(value = "/account/logout",
        method = RequestMethod.POST)
    ResponseEntity<Object> logout();

}