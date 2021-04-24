package com.zoomcare.candidatechallenge.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import com.zoomcare.candidatechallenge.service.PropertyService;

@RestController
public class EmployeePropertyController {

  @Autowired
  private EmployeeService employeeService;
  
  @Autowired
  private PropertyService propertyService;
  
  /*
   * Gets all the employees + their properties ordered by their supervisor
   */
  @RequestMapping("/employees")
  public List<Employee> findEmployees() {
    List<Employee> employees = employeeService.getAll();
    HashMap<Long, List<Property>> propertySet = new HashMap<Long, List<Property>>();
    
    // Go through the employees and assign properties to each employee
    for (Employee employee: employees) {
    	if (propertySet.containsKey(employee.getId())) {
    		employee.setProperties(propertySet.get(employee.getId()));
    	} else {
    		List<Property> propertyList = propertyService.getById(employee.getId());
    		propertySet.put(employee.getId(), propertyList);
    		employee.setProperties(propertyList);
    	}
    }
    
    return employees;
  }
  
  /*
   * Gets a employee and its properties.
   */
  @RequestMapping("/employee/{employeeId}")
  public Employee findEmployee(@PathVariable Long employeeId) {
    Employee employee = employeeService.getById(employeeId);
    employee.setProperties(propertyService.getById(employeeId));
    return employee;
  }
  
  @RequestMapping("/properties")
  public List<Property> findProperties() {
    return propertyService.getAll();
  }

}
