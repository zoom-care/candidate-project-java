package com.zoomcare.candidatechallenge.service;

import java.util.List;

import com.zoomcare.candidatechallenge.entity.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();

	Employee findEmployeeById(Long id);

	List<Employee> findBySupervisorId(Long id);
	
	Employee save(Employee employee);

}
