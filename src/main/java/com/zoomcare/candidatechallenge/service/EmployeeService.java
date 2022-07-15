package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;

/**
 * Defines the methods associated with the Employee operations, 
 * which must be implemented
 * 
 * @author Sergio de la Torre
 * @see EmployeeDto
 * @version 1.0
 * @since 2022-07-14
 */
public interface EmployeeService {
	
	/**
	 * Returns an employee by:
	 *  	@param employeeId
	 */
	EmployeeDto getEmployeeById(Long employeeId);

}
