package com.zoomcare.candidatechallenge.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.entity.Property;
import com.zoomcare.candidatechallenge.model.EmployeeDto;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl implements EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<Object> getAll() {
		List<Map<String, Object>> response = new ArrayList<Map<String,Object>>();
		
		List<Employee> employees = employeeService.getAllEmployees();
		
		employees.forEach(employee -> {
			Map<String, Object> resp = new HashMap<String, Object>();
			resp.putAll(getSupervisorData(employee));
			response.add(resp);
		});
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getById(Long id) {
		//First fetch the employee properties
		Employee employee = employeeService.findEmployeeById(id);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.putAll(getSupervisorData(employee));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	private Map<String, Object> getSupervisorData(Employee employee) {
		Map<String, Object> resp = new HashMap<String, Object>();
		
		//EmployeeResponse response = new EmployeeResponse();
		resp.putAll(getEmployeeData(employee));
		List<Map<String, Object>> listEmployees = new ArrayList<Map<String,Object>>();
		
		//then fetch the employees under supervision of current employee
		List<Employee> supervised = employeeService.findBySupervisorId(employee.getId());
		if (!supervised.isEmpty()) {
			supervised.forEach(emp -> {
				listEmployees.add(getEmployeeData(emp));
			});
			
			resp.put("employees", listEmployees);
		}
		return resp;
	}
	
	private Map<String, Object> getEmployeeData(Employee employee) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("id", employee.getId());
		Map<String, String> properties = new HashMap<String, String>();
		employee.getProperties().forEach(prop -> {
			properties.put(prop.getKey(), prop.getValue());
		});
		
		response.putAll(properties);
		
		return response;
	}

	@Override
	public ResponseEntity<Object> addEmployee(EmployeeDto empl) {
		System.out.println("Empl" +  empl);
		Employee employee = new Employee();
		employee.setSupervisorId(empl.getSupervisorId());
		List<Property> properties = new ArrayList<Property>();
		empl.getProperties().forEach(prop -> {
			Property property = new Property();
			property.setKey(prop.getKey());
			property.setValue(prop.getValue());
			property.setEmployee(employee);
			properties.add(property);
		});		
		employee.setProperties(properties);
		
		Employee added = employeeService.save(employee);

		return new ResponseEntity<>("Added succesfully with ID: " + added.getId(), HttpStatus.OK);
	}

}
