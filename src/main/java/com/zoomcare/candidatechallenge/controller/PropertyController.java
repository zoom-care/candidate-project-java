package com.zoomcare.candidatechallenge.controller;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "properties")
public class PropertyController {

    private final PropertyService propertiesService;
    @Autowired
    public PropertyController(PropertyService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<List<Property>> getAllEmployees(){
        List<Property> employees = propertiesService.topProperties();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
