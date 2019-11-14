package com.zoomcare.candidatechallenge.rest;

import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeResource {

    @Autowired
    EmployeeService service;

    @GetMapping
    public @ResponseBody List getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/id/{id}")
    public @ResponseBody List getEmployeeById(@PathVariable Long id) {
        return service.getEmployeesById(id);
    }

}
