package com.zoomcare.candidatechallenge.controllers;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/top")
    public List<EmployeeDto> getTopLevelEmployees() throws Exception {
        return employeeService.getTopLevelEmployees();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable("employeeId") int employeeId) throws Exception {
        return employeeService.getEmployeeById(employeeId);
    }
}
