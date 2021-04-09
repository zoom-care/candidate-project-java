package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;


@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = "application/json", path = "/toplevelemployees")
    @ResponseBody
    public List<EmployeeDTO> getTopLevelEmployees() {
        List<EmployeeDTO> employees = employeeService.getTopLevelEmployees();
        return employees;
    }

    @GetMapping(produces = "application/json", path = "/employeebyid/{id}")
    @ResponseBody
    public EmployeeDTO getEmployeesByID(@PathVariable("id") BigInteger id) {
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            return employee;
    }
}
