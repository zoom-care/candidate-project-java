package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity getById(@RequestParam(required = false) Long id) {
        return id == null ?
                ResponseEntity.ok(employeeService.getAll()) :
                ResponseEntity.ok(employeeService.getById(id));
    }
}

