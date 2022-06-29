package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResponseEntity getListAllEmployee(){
        return  ResponseEntity.ok(service.getAllEmployee());
    }

    @RequestMapping(value = "list/{employeeId}",method = RequestMethod.GET)
    public ResponseEntity getEmployeeId(@PathVariable("employeeId") BigInteger employeeId){
        return  ResponseEntity.ok(service.getEmployeeId(employeeId));
    }
}
