package com.zoomcare.candidatechallenge.Service;

import java.util.List;
import java.util.Optional;

import com.zoomcare.candidatechallenge.entity.Employee;

public interface EmployeeService {
	public List<Employee> findAllEmployees();

	public Optional<Employee> findById(Long id);
}
