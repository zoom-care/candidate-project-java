package com.zoomcare.candidatechallenge.controllers;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.map.EmployeeData;
import com.zoomcare.candidatechallenge.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<EmployeeData> getEmployee(BigInteger id) {
		return employeeService.getEmployeeData(id);
	}
	
	@GetMapping("/topLevel")
	public ResponseEntity<List<EmployeeData>> getTopLevelEmployee() {
		return employeeService.getTopLevelEmployee();
	}

}
