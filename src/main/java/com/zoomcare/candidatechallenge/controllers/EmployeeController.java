package com.zoomcare.candidatechallenge.controllers;

import java.util.ArrayList;
import java.util.List;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;
  
  @GetMapping("/employee")
	public ResponseEntity<List<Employee>> employee(@RequestParam(value = "id", defaultValue = "") String id) {
    // Return a list of a single employee and their direct report hierarchy if an ID was passed in the request
    // Return a list of all supervisors and the full direct report hierarchy if no ID was passed
    List<Employee> employees = new ArrayList<>();
    if (id.equals("")) {
      employees = employeeService.getAllEmployees();
    } else {
      employees = employeeService.getEmployeeById(Long.valueOf(id));
    }
    return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
  }
}
