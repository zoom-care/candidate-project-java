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
import java.util.Optional;


@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeWithReports>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EmployeeWithReports> getById(@PathVariable Long id) {
        return Optional.ofNullable(employeeService.getById(id))
                .map(ResponseEntity::ok) // if exists, return 200 with body
                .orElse(ResponseEntity.notFound().build()); // otherwise return a 404
    }
}

