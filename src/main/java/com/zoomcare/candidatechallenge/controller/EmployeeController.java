package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
    public String employees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "employees/{id}", method = RequestMethod.GET, produces = "application/json")
    public String findEmployeeById(@PathVariable Integer id) {
        return employeeService.findEmployeeById(id);
    }
}