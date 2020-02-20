package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Employee Management System")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Get employee by ID")
    @RequestMapping(
            value = "/employee/{id}",
            method = RequestMethod.GET)
    public Employee employee(@PathVariable final Long id)
            throws EmployeeNotFoundException {
        return this.employeeService.getEmployee(id);
    }

    @ApiOperation(value = "Get top-level employees")
    @GetMapping("/employees/toplevel")
    public List<Employee> employees() {
        return this.employeeService.getTopLevel();
    }
}
