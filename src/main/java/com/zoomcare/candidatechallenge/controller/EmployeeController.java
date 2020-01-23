package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
