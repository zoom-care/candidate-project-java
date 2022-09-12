package com.zoomcare.candidatechallenge.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.entities.EmployeeResource;
import com.zoomcare.candidatechallenge.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("employee/getTopLevel")
	public List<EmployeeResource> getTopLevelEmployees(){
		return employeeService.getTopLevelEmployees();
	}
	
	@GetMapping("employee")
	public EmployeeResource getEmployeeById(@RequestParam("employeeId") Integer employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}
	
	
}
