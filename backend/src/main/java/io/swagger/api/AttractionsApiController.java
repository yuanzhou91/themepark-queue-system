package io.swagger.api;

import io.swagger.entities.AttractionEntity;
import io.swagger.model.Attraction;
import io.swagger.model.Attractions;
import io.swagger.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repositories.AttractionRepository;
import io.swagger.repositories.GuestRepository;
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
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")

@Controller
public class AttractionsApiController implements AttractionsApi {

    private static final Logger log = LoggerFactory.getLogger(AttractionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private AttractionRepository attractionRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public AttractionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Attractions> getAttractions() {
        List<AttractionEntity> attractionEntities = attractionRepository.getAllAttractions();
        List<Attraction> attractions = attractionEntities.stream().map(entity -> new Attraction().id(entity.getId()).location(entity.getLocation()).name(entity.getAttractionName())).collect(Collectors.toList());
        Attractions attractionsResponse = new Attractions();
        attractionsResponse.addAll(attractions);
        return new ResponseEntity<Attractions>(HttpStatus.OK).ok(attractionsResponse);
    }

}
