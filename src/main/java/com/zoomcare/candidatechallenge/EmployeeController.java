package com.zoomcare.candidatechallenge;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class EmployeeController {
	
	EmployeeDAO employeeDAO = new EmployeeDAO();

	@RequestMapping("/TopLevelEmployees")
	public ArrayList<Employee> topLevelEmployees() {
						
		return employeeDAO.getEmployeesBySupervisorId(null);
	}
	
	@RequestMapping("/Employee")
	public Employee employee(@RequestParam(name = "employeeid") int id) {
			
		return(employeeDAO.getEmployeeData(id));
	}
	
	
	
}
