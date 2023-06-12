package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.EmployeeResponse;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService service;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable @NotNull Long id) {
        Optional<EmployeeResponse> employee = service.getEmployee(id);
        return employee.isPresent() ? ResponseEntity.ok(employee.get()) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return service.getEmployees();
    }
}
