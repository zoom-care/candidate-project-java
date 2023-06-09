package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.EmployeeResponse;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService service;

    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable @NotNull Long id) {
        return service.getEmployee(id);
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return service.getEmployees();
    }
}
