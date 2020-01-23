package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getTopLevelEmployees () {
        List<Employee> employees = employeeService.getTopLevelEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Employee> getEmployee (@PathVariable Integer id) {
        Employee employee = employeeService.getEmployee(id);
        return ResponseEntity.ok().body(employee);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
