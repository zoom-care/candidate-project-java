package com.zoomcare.candidatechallenge.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.repo.entity.Employee;
import com.zoomcare.candidatechallenge.repo.entity.EmployeeProperty;

@Component
@Transactional
public class EmployeeConverter {

	@Inject
	private EmployeeRepository employeeRepository;

	public List<EmployeeDto> findTopLevelAndConvert() {
		return convert(employeeRepository.findBySupervisorIdIsNull());
	}

	public EmployeeDto findEmployeeAndConvert(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return convert(employee.get());
		}
		return null;
	}

	private List<EmployeeDto> convert(List<Employee> employees) {
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		if (employees != null) {
			for (Employee employee : employees) {
				employeeDtos.add(convert(employee));
			}
		}
		return employeeDtos;
	}

	private EmployeeDto convert(Employee employee) {
		EmployeeDto dto = new EmployeeDto();
		dto.setId(employee.getId());
		Map<String, String> properties = new HashMap<String, String>();
		for (EmployeeProperty employeeProperty : employee.getEmployeeProperties()) {
			properties.put(employeeProperty.getKey(), employeeProperty.getValue());
		}
		dto.setProperties(properties);
		Set<EmployeeDto> directReportDtos = new HashSet<>();
		for (Employee directReport : employee.getDirectReports()) {
			directReportDtos.add(convert(directReport));
		}
		dto.setDirectorReport(directReportDtos);
		return dto;

	}
}
