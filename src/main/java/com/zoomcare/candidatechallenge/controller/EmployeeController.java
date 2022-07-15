package com.zoomcare.candidatechallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.dto.EmployeeAllDto;
import com.zoomcare.candidatechallenge.service.EmployeeService;

/**
 * Receive requests related to employees
 * <p>
 * Includes the following operations
 * <ul>
 * 	<li> Search by id
 * 	<li> Lists employees who are supervisors
 * </ul>
 * 
 * @author Sergio de la Torre
 * @see EmployeeAllDto
 * @see EmployeeService
 * @version 1.2
 * @since 2022-07-14
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * Receive the request for the search by id
	 * @param id
	 * 			employee id
	 * @return	ResponseEntity with the employee
	 * 			{@link ResponseEntity<EmployeeAllDto>}
	 */
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeAllDto> getById(@PathVariable("id") Long employeeId){
		EmployeeAllDto employeeDto = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(employeeDto, HttpStatus.OK);
	}
	
	/**
	 * Receive the request for listing employees who are supervisors
	 * @return	list of employees
	 * 			{@link List<EmployeeAllDto>}
	 */
	@GetMapping("/top")
	public List<EmployeeAllDto> getTopEmployees(){
		return employeeService.getTopEmployees();
	}

}
