package com.zoomcare.candidatechallenge.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.constants.ApplicationConstants;
import com.zoomcare.candidatechallenge.dao.EmployeeDAO;
import com.zoomcare.candidatechallenge.exception.DataRequiredException;
import com.zoomcare.candidatechallenge.exception.EmployeeException;
import com.zoomcare.candidatechallenge.model.Employee;

/**
 * Repository for employees
 */
@Repository
public class EmployeeRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeRepository.class);
	
	private final EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeRepository(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	/**
	 * Get employee information
	 * @param id - Employee ID
	 * @return Employee information
	 * @throws EmployeeException
	 */
	public Employee getEmployeeById(Long id) throws EmployeeException {
		
		if (null == id) {
			LOG.error("Employee ID is null");
			throw new DataRequiredException(ApplicationConstants.EMPLOYEE_ID_REQUIRED);
		}
		
		LOG.info(String.format("Getting Employee by Id: %d", id));
		return employeeDAO.findById(id).orElse(null);
	}
	
	/**
	 * Get all employees
	 * @return Employees
	 */
	public Iterable<Employee> getAllEmployees(){
		LOG.info("Get all employees");
		return employeeDAO.findAll(); 
	}

}
