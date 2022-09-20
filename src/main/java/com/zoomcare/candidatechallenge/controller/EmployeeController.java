package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exceptions.NotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api/v1")
@Tag(name = "ZOOM+Care Candidate Code Challenge", description = "Swagger documentation for RestAPI")
public interface EmployeeController {

    @Operation(summary = "Retrieve all employees")
    @GetMapping("/employee")
    ResponseEntity<List<Employee>> getAllEmployees();

    @Operation(summary = "Retrieve an employee by its id")
    @GetMapping("/employee/{employeeId}")
    ResponseEntity<Optional<Employee>> getEmployeeById(
            @PathVariable(value = "employeeId") Long employeeId) throws NotFoundException;

    @Operation(summary = "Retrieve a supervisor by its id")
    @GetMapping("/supervisor/{supervisorId}")
    ResponseEntity<List<Employee>> getSupervisorById(
            @PathVariable(value = "supervisorId") Integer supervisorId) throws NotFoundException;

}

