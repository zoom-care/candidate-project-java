package com.zoomcare.candidatechallenge.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;

public interface EmployeeService {

	/***
	 * 
	 * Pre: employeeId must be valid
	 * 
	 * Post: The employee's Id and all properties as well as a nested list of all direct reports for that employee
	 * 
	 * @param employeeId
	 * @return EmployeeDto
	 */
	public EmployeeDto getEmployeeById(Long employeeId);
	
	/***
	 * 
	 * Pre: Employees could or not to exist. If they exist the containing info must be valid
	 * 
	 * Post: List of all top-level employees who doesn't have a supervisor
	 * 
	 * @param pagination
	 * @return List<EmployeeDto> 
	 */
	public List<EmployeeDto> getListTopEmployees ();
		
}
