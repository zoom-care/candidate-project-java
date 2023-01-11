package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.BO.EmployeeBO;
import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeServices;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees/v1")
public class EmployeeController {
    EmployeeServices employeeServices ;
    ModelMapper mapper;
    public EmployeeController (EmployeeServices employeeServices ,ModelMapper mapper ){
        this.employeeServices = employeeServices;
        this.mapper= mapper;
    }

    @GetMapping(path = "/{empId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeBO> getEmployeeById(@PathVariable(value="empId") Long employeeId) {
        Employee employee = employeeServices.getEmployeebyId(employeeId);
        EmployeeBO employeeBO= mapper.map(employee, EmployeeBO.class);
        return ResponseEntity.ok(employeeBO);
    }
    @GetMapping(path = "/topLevelEmployees",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeBO>> getTopLevelEmployees() {
        List<Employee> topLevelEmployees = employeeServices.getTopLevelEmployee();
        List<EmployeeBO> topLevelEmployeesBO = topLevelEmployees.stream().map(e -> mapper.map(e, EmployeeBO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(topLevelEmployeesBO);
    }
}
