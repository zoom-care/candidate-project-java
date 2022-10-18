package com.zoomcare.candidatechallenge.Employee.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.Employee.entities.Employee;
import com.zoomcare.candidatechallenge.Employee.entities.Property;
import com.zoomcare.candidatechallenge.Employee.repositories.EmployeeRepository;
import com.zoomcare.candidatechallenge.Employee.repositories.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomcare.candidatechallenge.Employee.EmployeeData;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    
    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable("id") Integer id) throws Exception {
      Optional<Employee> employee = employeeRepository.findById(id);
      List<Property> property = propertyRepository.findByEmployeeId(id);
      return new ResponseEntity(new EmployeeData( employee, property ), HttpStatus.OK);
    }
  
    
}