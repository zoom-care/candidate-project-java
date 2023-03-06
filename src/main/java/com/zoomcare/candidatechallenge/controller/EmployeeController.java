package com.zoomcare.candidatechallenge.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/toplevel")
	public List<EmployeeDTO> getTopLevelEmployees(){
		return employeeService.getTopLevelEmployees();
	}
	
	@GetMapping("/{id}")
	public EmployeeDTO getById(@PathVariable("id") Long employeeId) {
		return employeeService.getEmployeeByID(employeeId);
	}
	
	

}
