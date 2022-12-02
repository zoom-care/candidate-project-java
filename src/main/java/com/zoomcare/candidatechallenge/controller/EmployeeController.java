package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

//get specific employee details based on id
    @GetMapping(value="/api/employee/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeDto getEmployee(@PathVariable(name="id") Long id){
return employeeService.getEmployee(id);
    }

//get list of top level employees
    @GetMapping(value="/api/employee/topLevel")
    @ResponseStatus(HttpStatus.OK)
    List<EmployeeDto> getTopLevelEmployeeList(){
        return employeeService.getTopLevelEmployeeList();
    }
}
