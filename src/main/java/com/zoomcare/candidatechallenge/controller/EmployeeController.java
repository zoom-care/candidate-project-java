package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.EmployeeWithReports;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeWithReports> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeWithReports>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }
}

