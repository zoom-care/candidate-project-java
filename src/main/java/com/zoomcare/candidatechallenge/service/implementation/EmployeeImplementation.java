package com.zoomcare.candidatechallenge.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.lib.EmployeeMapStruct;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@Service
public class EmployeeImplementation implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeDto getEmployeeById(Long employeeId) {
	 Optional<Employee> employeeInfo = employeeRepository.findById(employeeId);
	 
	 if (!employeeInfo.isPresent())
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found for this id ::" + employeeId);
	 
	return EmployeeMapStruct.MAPSTRUCT_INSTANCE.employeeToDtoEmployee(employeeInfo.get());
	}
	
	public List<EmployeeDto> getListTopEmployees (){
		Page<Employee> listEmployees = (Page<Employee>) employeeRepository.findAll();
		 
		return listEmployees
				 .stream()
				 .filter(employee -> employee.getSupervisor().equals(null))
				 .map(EmployeeMapStruct.MAPSTRUCT_INSTANCE::employeeToDtoEmployee)
				 .collect(Collectors.toList())
				 ;
				 
	}
	
	
}
