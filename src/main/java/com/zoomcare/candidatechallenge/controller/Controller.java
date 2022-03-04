package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class Controller {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/employee/{id}")
    Employee getEmployee(@PathVariable(value = "id") Integer id) {
        return employeeRepo.getById(id);
    }

    @GetMapping("/employees")
    List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }


}
