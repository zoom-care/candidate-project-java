package com.zoomcare.candidatechallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.employee.EmployeeJdbc;
/**
 * @author Pabel Lopez
 */

@RestController
public class EmployeeController {

    @Autowired
    EmployeeJdbc repository;

    @GetMapping("employee/{id}")
    public List getEmployeeById (@PathVariable final long id){        
        return repository.getEmployeeById(id);
    }
    
    @GetMapping("employee/")
    public List getTopLevelEmployees (){
        return repository.getTopLevelEmployees();
    }
}