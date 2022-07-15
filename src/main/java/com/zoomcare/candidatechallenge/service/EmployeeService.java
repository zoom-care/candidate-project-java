package com.zoomcare.candidatechallenge.service;

import java.util.List;

import com.zoomcare.candidatechallenge.dto.EmployeeAllDto;

/**
 * Defines the methods associated with the Employee operations, 
 * which must be implemented
 * 
 * @author Sergio de la Torre
 * @see EmployeeAllDto
 * @version 1.2
 * @since 2022-07-14
 */
public interface EmployeeService {
	
	/**
	 * Returns an employee by:
	 *  	@param employeeId
	 */
	EmployeeAllDto getEmployeeById(Long employeeId);
	
	/**
	 * Returns a list of employees that are supervisors 
	 * of at least another employee
	 */
	List<EmployeeAllDto> getTopEmployees ();

}
