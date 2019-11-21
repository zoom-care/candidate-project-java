package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

//    @Autowired
//    private EmployeeRepository employeeRepository;

    @GetMapping("/employees/{employee_id}")
    String employees(@PathVariable("employee_id") Long employeeId) {
        log.info("Retrieving employee by id {}", employeeId);

       // Employee employee = employeeRepository.getEmployeesByID(employeeId);

        return "done";
    }

    @GetMapping("/employees")
    String employees() {

        //List<Employee> employees = employeeRepository.findAll();

        return "done";
    }


}
