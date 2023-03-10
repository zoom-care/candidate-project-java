package com.zoomcare.candidatechallenge.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomcare.candidatechallenge.model.EmployeeDto;

@RequestMapping("/employee")
public interface EmployeeController {
	
	@GetMapping("/")
	ResponseEntity<Object> getAll();
	
	@GetMapping("/{id}")
	ResponseEntity<Object> getById(@PathVariable Long id);
	
	@PostMapping("/")
	ResponseEntity<Object> addEmployee(@RequestBody EmployeeDto employee);

}
