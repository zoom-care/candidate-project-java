package com.zoomcare.candidatechallenge.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.domain.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

// Creation Restful webservices using RestController annotation
@RestController
public class EmployeeController {
	
		
		@Autowired
		private EmployeeRepository eRepository;
		
		
		// Define the endpoint that the method is mapped to : RequestMapping
		@RequestMapping ("/employees")
		public Iterable<Employee> getEmployees(){
			return eRepository.findAll();
			
		}
		
		// Define the endpoint that the method is mapped to : RequestMapping
		// Using PathVairable for id employee
		@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
		@ResponseBody
		public Optional <Employee> getEmployeeById(
		  @PathVariable("id") long id) {
		    return eRepository.findById(id);
		}

	

}
