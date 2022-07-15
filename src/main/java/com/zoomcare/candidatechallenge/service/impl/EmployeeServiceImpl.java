package com.zoomcare.candidatechallenge.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.dto.EmployeeAllDto;
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
		Employee employee = employeeRepository.findById(employeeId).orElse(new Employee());
		return mapper.map(employee, EmployeeAllDto.class);
	}

}
