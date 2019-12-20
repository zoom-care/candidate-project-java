package com.zoomcare.candidatechallenge.services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.repo.EmployeeConverter;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Inject
	private EmployeeConverter employeeConverter;

	@Override
	public EmployeeDto findEmployeeById(long id) {
		return employeeConverter.findEmployeeAndConvert(id);
	}

	@Override
	public List<EmployeeDto> findTopLevelEmployees() {
		return employeeConverter.findTopLevelAndConvert();
	}

}