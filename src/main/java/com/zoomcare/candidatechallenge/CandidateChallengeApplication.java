package com.zoomcare.candidatechallenge;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class CandidateChallengeApplication
{
	@RequestMapping("/")
	String home() {
		EmployeeController controller = new EmployeeController();
		List<Employee> employees = controller.getAll();
		if (employees == null) {
			return "employees is null";
		}
		Employee employee = employees.get(0);
		if (employee == null) {
			return "employee is null";
		}
		return "Employee ID: " + employee.getId() + ", Supervisor ID: " + employee.getSupervisorId();
	}

	public static void main(String[] args)
	{
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

}
