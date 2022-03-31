package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.dto.EmployeePropertiesDTO;
import com.zoomcare.candidatechallenge.services.EmployeePropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeePropertiesService employeePropertiesService;

    @GetMapping("zoomcare/topemployees")
    Iterable<EmployeePropertiesDTO> getTopEmployees() {
        return employeePropertiesService.getTopEmployees();
    }

    @GetMapping("zoomcare/employees/{id}")
    EmployeePropertiesDTO findEmployee(@PathVariable BigInteger id) {
        return employeePropertiesService.getEmployeeById(id);
    }
}
