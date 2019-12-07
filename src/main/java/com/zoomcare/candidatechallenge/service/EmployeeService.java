package com.zoomcare.candidatechallenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

@Service
public class EmployeeService {

	final Logger logger = LogManager.getLogger(EmployeeService.class.getSimpleName());

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getTopLevelEmployees() {

		List<Employee> topEmployees = new ArrayList<>();

		employeeRepository.findBySupervisorIdIsNull().forEach(topEmployees::add);

		logger.info("getTopLevelEmployees(): Found {} top level employees ", topEmployees.size());
		return topEmployees;
	}

	public Employee getEmployeeById(Long id) {

		Optional<Employee> employee = employeeRepository.findById(id);

		if (employee.isEmpty()) {
			// will get caught by the REST API exception handler
			throw new EmployeeNotFoundException(String.format("Employee with id:[%s] not found", id));
		}

		Employee emp = employee.get();
		
		logger.info("getEmployee(): Found employee with id:{}. Number of reporting employees:{} ", emp.getId(),emp.getReportingEmployees().size());

		return emp;
	}
}