package com.zoomcare.candidatechallenge.employee;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

	@NonNull
	private final EmployeeRepository employeeRepository;

	/**
	 * Finds an employee by id
	 *
	 * @param id the id of the employee
	 * @return The {@link com.zoomcare.candidatechallenge.employee.Employee} if found or null if not found
	 */
	@GetMapping(value = "")
	public Employee findById(@RequestParam long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	/**
	 * Gets all employees with no supervisor
	 *
	 * @return A list of {@link com.zoomcare.candidatechallenge.employee.Employee} if any or an empty list otherwise
	 */
	@GetMapping(value = "/topSupervisors")
	public List<Employee> getAllTopLevelEmployees() {
		return employeeRepository.findEmployeeBySupervisorIsNull();
	}
}
