package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.entities.GuestEntity;
import io.swagger.entities.OperatorEntity;
import io.swagger.model.LoginParams;
import io.swagger.model.LoginResult;
import io.swagger.repositories.GuestRepository;
import io.swagger.repositories.OperatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")

@Controller
public class AccountApiController implements AccountApi {

    private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<LoginResult> login(@ApiParam(value = "login params" ,required=true )  @Valid @RequestBody LoginParams body) {
        if (body.getIsAdmin() != null && body.getIsAdmin() == true) {
            OperatorEntity operator = operatorRepository.findOperatorEntityBy(body.getUsername(), body.getPassword());
            if (operator == null) {
                return ResponseEntity.ok(new LoginResult().type(body.getType()).status("error").currentAuthority("admin"));
            }
            return ResponseEntity.ok(new LoginResult().type(body.getType()).status("ok").currentAuthority("admin").setUserId(operator.getId()));
        } else {
            GuestEntity guest = guestRepository.findUserByEmailAndPassword(body.getUsername(), body.getPassword());
            if (guest == null) {
                return ResponseEntity.ok(new LoginResult().type(body.getType()).status("error").currentAuthority("user"));
            }
            return ResponseEntity.ok(new LoginResult().type(body.getType()).status("ok").currentAuthority("user").setUserId(guest.getId()));
        }
    }

    public ResponseEntity<Object> logout() {
        return ResponseEntity.noContent().build();
    }

}
