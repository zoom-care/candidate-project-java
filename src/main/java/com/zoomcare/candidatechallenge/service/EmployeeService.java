package com.zoomcare.candidatechallenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.entity.EmployeeProperty;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<EmployeeDTO> getTopLevelEmployees() {
		return employeeRepository.findAllBySupervisorIdIsNull().stream()
				.map(this::toEmployeeDTO)
				.collect(Collectors.toList());
	}

	public EmployeeDTO getEmployeeByID(Long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (employee.isPresent()) {
			return toEmployeeDTO(employee.get());
		} else {
			return null;
		}
	}
	
	private EmployeeDTO toEmployeeDTO(Employee employee) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(employee.getEmployee_id());
		dto.setSupervisorId(employee.getSupervisorId() != null ? employee.getSupervisorId() : null );
		dto.setProperties(employee.getProperties().stream().distinct()
				.collect(Collectors
				.toMap(EmployeeProperty::getKey, EmployeeProperty::getValue)));
		dto.setEmployees(employee.getEmployees()
				.stream()
				.map(this::toEmployeeDTO).collect(Collectors.toList()));
		return dto;		
	}	
}
