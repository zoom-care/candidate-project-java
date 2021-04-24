package com.zoomcare.candidatechallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.model.EmployeeProperty;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	

	@RequestMapping("/")
	public String home() {
		
		return "home.jsp";
	}
	
//	@RequestMapping("/searchById/{id}") // {} telling spring its a variable
//	public Employee getEmployee(@PathVariable String id) { // passing the variable, same name
//		return employeeService.getEmployee(id);
//	}
	
	@RequestMapping("/all")
	public List<EmployeeProperty> getAllRecords(){
		return employeeService.getAllRecords();
	};
	
	@RequestMapping("/searchById/{id}")
	public List<EmployeeProperty> getEmployeeWithProperties(@PathVariable String id){
		// check if its a number
		int idAsInt;
		try {
			idAsInt = Integer.parseInt(id);
		} catch(NumberFormatException e) {
			System.out.println("Not a number!");
			return null;
		}
		return employeeService.getEmployeeWithProperties(idAsInt);
	};
	
	@RequestMapping (value = "/**", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> defaultPath() {
	    System.out.println("Unmapped request handling!");
	    return new ResponseEntity<String>("Unmapped request", HttpStatus.OK);
	}
	
}
