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
		List<Employee> employees = controller.getTopLevel();
		if (employees == null) {
			return "Error: employees is null";
		}
		String display = "<h3>Organization: </h3><ul>";
		for (Employee employee : employees) {
			display += "<li>" + formatEmployee(employee) + "</li>";
		}
		display += "</ul>";
		return formatPage(display);
	}

	@RequestMapping(value = "/employee")
	public String viewEmployee(int id) throws SQLException {
		EmployeeController controller = new EmployeeController();
		Employee employee = controller.get(id);
		if (employee == null) {
			return "Error: employee is null";
		}
		return formatPage(formatEmployee(employee));
	}

	public static void main(String[] args) {
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

	// Create some formatting functions for ease of use proving endpoints' functionality

	private String formatPage(String text) {
		return text;
	}

	private String formatEmployee(Employee employee) {
		return formatEmployee(employee, 0);
	}

	private String formatEmployee(Employee employee, int indent) {
		String formatted = "{ ID: " + employee.getId() + ", "
				+ employee.getProperties().toString().replace("[", "").replace("]", "") + " }";

		if (!employee.getDirectReports().isEmpty()) {
			formatted += "<ul>";
			for (Employee directReport : employee.getDirectReports()) {
				formatted += "<li>" + formatEmployee(directReport, indent + 1) + "</li>";
			}
			formatted += "</ul>";
		}

		// Add links
		formatted = formatted.replaceAll("\\{ ID: (\\d+)", "{ ID: <a href=\"/employee?id=$1\">$1</a>");

		return formatted;
	}
}
