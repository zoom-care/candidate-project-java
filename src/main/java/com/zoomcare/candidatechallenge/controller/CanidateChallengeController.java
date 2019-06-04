package com.zoomcare.candidatechallenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zoomcare.candidatechallenge.entity.projection.EmployeeProjection;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

@Controller
@RequestMapping("/api/employees")
public class CanidateChallengeController {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@GetMapping("/top")
	@ResponseBody
	public List<EmployeeProjection> getTopLevel() {
		List<EmployeeProjection> topLevelEmployees = employeeRepo.findBySupervisorIsNull();
		
		if (topLevelEmployees == null || topLevelEmployees.isEmpty()) {
			throw new EmployeeNotFoundException("[CanidateChallengeController.getTopLevel] "
					+ "Cannot Find Any Top Level Employee(s)");
		}
		
		return topLevelEmployees;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public EmployeeProjection getEmployee(@PathVariable("id") Long id) {
		Optional<EmployeeProjection> employee = employeeRepo.getById(id);
		
		return employee.orElseThrow(() -> new EmployeeNotFoundException("[CanidateChallengeController.getEmployee] "
				+ "Cannot Find Top Level Employee(s) with id = " + id));
	}
}
