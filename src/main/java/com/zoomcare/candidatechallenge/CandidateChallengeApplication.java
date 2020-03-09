package com.zoomcare.candidatechallenge;

import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class CandidateChallengeApplication {
	@RequestMapping("/")
	String home() throws SQLException {
		EmployeeController controller = new EmployeeController();
		Employee employee = controller.get(1);
		if (employee == null) {
			return "employee is null";
		}
		List<Employee> directReports = employee.getDirectReports();
		return "Employee ID: " + employee.getId() + ", Supervisor ID: " + employee.getSupervisorId() + ", Direct Reports: " + directReports.toString();
	}

	public static void main(String[] args)
	{
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

}
