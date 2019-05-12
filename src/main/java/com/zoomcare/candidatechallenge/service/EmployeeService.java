package com.zoomcare.candidatechallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;

/**
 * Not absolutely needed for this challenge since all access is read only
 * @author lalit
 *
 */
@Component
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getTopLevelEmployees() {
		
		return employeeRepository.findBySupervisorIsNull();
		
	}

	public Employee getEmployeeById(long id) {
		
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		return optionalEmployee
				.orElseThrow(() -> new EmployeeNotFoundException("Couldn't find an Employee with id: " + id));
		
	}

}
