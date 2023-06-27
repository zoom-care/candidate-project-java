package com.zoomcare.candidatechallenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.model.EmployeeEntity;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.repository.PropertyRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CandidateChallengeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PropertyRepository propertiesRepository;
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees(@RequestParam(required = false) String title) {
		try {
			List<EmployeeEntity> employeeData = new ArrayList<EmployeeEntity>();
				employeeRepository.findAll().forEach(employeeData::add);
				
			if (employeeData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employeeData, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") long id) {
		Optional<EmployeeEntity> employeeData = employeeRepository.findById(id);

		if (employeeData.isPresent()) {
			return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
