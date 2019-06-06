package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employees")
    public ResponseEntity getEmployeesFromTop() {
        List<Employee> employeeList = employeeService.getEmployeesFromTop();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @RequestMapping("/employees/{id}")
    public ResponseEntity getEmployee(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployee(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            String errorJson = "{\"error\":\"" + e.getMessage() + "\"}";
            return new ResponseEntity<>(errorJson, HttpStatus.NOT_FOUND);
        }
    }
}
