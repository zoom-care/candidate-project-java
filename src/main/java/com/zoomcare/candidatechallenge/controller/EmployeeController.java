package com.zoomcare.candidatechallenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import com.zoomcare.candidatechallenge.service.implementation.EmployeeImplementation;
import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.exception.ResourceNotFoundException;
import com.zoomcare.candidatechallenge.model.EmployeeEntity;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController  {
	
	@Autowired
    private EmployeeService employeeService;
	
	//get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employeeId") Long employeeId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return new ResponseEntity<EmployeeDto>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
	}
	
	//create get all employees api
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDto>> getListTopEmployees() {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(employeeService.getListTopEmployees(), HttpStatus.OK);
	}

	
}
