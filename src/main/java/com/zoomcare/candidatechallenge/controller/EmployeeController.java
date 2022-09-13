package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public List<EmployeeDTO> findAllTopLevelEmployees() {
        return employeeService.findAllTopLevelEmployees();
    }

    @GetMapping("employee/{id}")
    public List<EmployeeDTO> findEmployeeById(@PathVariable("id") Integer employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

}
