package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.repo.EmployeeRepo;
import com.zoomcare.candidatechallenge.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping(value = "")
    public List<Employee> getAllEmployees(){
        return employeeRepo.getAllEmployees();
    }
    @GetMapping(value = "/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id){
        return employeeRepo.getEmployeeById(id);
    }
}
