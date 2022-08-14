package com.zoomcare.candidatechallenge.controllers;

import com.zoomcare.candidatechallenge.models.entity.Property;
import com.zoomcare.candidatechallenge.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<?> listEmployees() {
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<?> searchEmployeeId(@PathVariable Long employeeId) {
        List<Property> listEmployee = employeeService.findByEmployeeId(employeeId);
        Optional<Property> result = listEmployee.stream().findAny();
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

}
