package com.zoomcare.candidatechallenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.ServiceImpl.EmployeeServiceImpl;
import com.zoomcare.candidatechallenge.entity.Employee;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@GetMapping
	public List<Employee> getAllEmployess() {
		return employeeService.findAllEmployees();
	}

	@GetMapping
	@RequestMapping("/{employeeId}")
	public Optional<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
		return employeeService.findById(employeeId);
	}

}
