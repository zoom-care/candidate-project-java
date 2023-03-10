package com.zoomcare.candidatechallenge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findEmployeeById(Long id) {
		//return employeeRepository.findById(id).orElse(null);
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException());
	}

	@Override
	public List<Employee> findBySupervisorId(Long id) {
		return employeeRepository.findBySupervisorId(id);
	}
	
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

}
