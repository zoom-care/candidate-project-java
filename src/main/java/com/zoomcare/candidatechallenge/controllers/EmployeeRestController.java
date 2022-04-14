package com.zoomcare.candidatechallenge.controllers;


import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.models.entity.Employee;
import com.zoomcare.candidatechallenge.models.entity.Property;
import com.zoomcare.candidatechallenge.models.services.IEmployeeService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<HashMap<Employee, List<Property>>> index(){
		return employeeService.findAllWithProperties();
	}
	
	
	@GetMapping("/employees/employee")
	public Employee show(@RequestParam Long id){
		
		Employee employee = new Employee();


		return create(employee);
	}
	
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee){	

		return employeeService.save(employee);		
	}
		
	@PutMapping("/employees/{id}")
	public Employee update(@RequestBody Employee employee, @PathVariable Long id){
		Employee current = employeeService.findById(id);

		
		return employeeService.save(current);
	}
	
	@DeleteMapping("/employees/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		employeeService.delete(id);
	}
}
