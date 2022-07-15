package com.zoomcare.candidatechallenge.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.dto.EmployeeAllDto;
import com.zoomcare.candidatechallenge.exception.ResourceNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public EmployeeAllDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found"));
		return mapper.map(employee, EmployeeAllDto.class);
	}

	@Override
	public List<EmployeeAllDto> getTopEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream()
				/*Not sure if "top-level employees" means the ones that hasn't a supervisor or
				everyone that is supervisor of at least another employee*/
				.filter(e -> e.getSupervisor()==null || !e.getEmployees().isEmpty())
				.map(e -> mapper.map(e, EmployeeAllDto.class) )
				.collect(Collectors.toList());
	}

}
