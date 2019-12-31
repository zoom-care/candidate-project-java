package com.zoomcare.candidatechallenge;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeControler {
	@Autowired
	EmployeeRepository employeeRepo;

	@GetMapping("/employees")
	public Employee[] getEmployees(@RequestParam(value = "id", required = false) Long id) {
		Employee[] result = new Employee[] {};
		if (id == null) {
			List<Employee> employees = employeeRepo.findBySupervisorId(null);
			result = employees.toArray(result);
		} else {
			result = new Employee[] { getEmployeeById(id) };
		}
		return result;
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		Optional<Employee> optionalEntity = employeeRepo.findById(id);
		return optionalEntity.get();
	}

}
