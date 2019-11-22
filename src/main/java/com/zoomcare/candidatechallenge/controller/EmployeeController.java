package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exceptions.EmployeeInternalServerError;
import com.zoomcare.candidatechallenge.exceptions.EmployeeNotFound;
import com.zoomcare.candidatechallenge.dto.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{employee_id}")
    Employee employees(@PathVariable("employee_id") Long employeeId) throws EmployeeNotFound, EmployeeInternalServerError {
        log.info("Retrieving employee by id {}", employeeId);
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/employees")
    List<Employee> employees() throws EmployeeInternalServerError {
        log.info("Retrieving all employees");
        return employeeService.getAllEmployees();
    }
}
