package com.zoomcare.candidatechallenge.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/top-level")
	public List<Employee> getTopLevelEmployees() {

		return employeeService.getTopLevelEmployees();

	}

	@GetMapping("/{id}")
	public Employee getById(@PathVariable(required = true) long id) {

		return employeeService.getEmployeeById(id);

	}
}
