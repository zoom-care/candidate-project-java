package com.zoomcare.candidatechallenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  public List<Employee> getAllEmployees() {
    List<Employee> employeeTree = new ArrayList<>();
    for (Employee employee : attachPropertiesToEmployees(employeeRepository.getAllSupervisors())) {
      employeeTree.add(createEmployeeTree(employee));
    }
    return employeeTree;
  }

  public List<Employee> getEmployeeById(Long id) {
    List<Employee> employeeTree = new ArrayList<>();
    Employee employee = employeeRepository.getEmployee(id);
    // If we can't find the employee based on the ID provided, we'll return an empty list
    if (employee == null) {
      return employeeTree;
    }
    List<Map<String, Object>> properties = employeeRepository.getEmployeeProperties(id);
    employee = attachPropertiesToEmployee(employee, properties);
    employeeTree.add(createEmployeeTree(employee));
    return employeeTree;
  }

  private Employee createEmployeeTree(Employee currentEmployee) {
    List<Employee> directReports = attachPropertiesToEmployees(employeeRepository.getEmployeesBySupervisorId(currentEmployee.getId()));
    if (directReports.size() == 0) {
      // we've reached the end of this branch, no more direct reports to identify
      return currentEmployee;
    } else {
      for (Employee employee : directReports) {
        // recursively add direct reports to continue creating the employee hierarchy
        currentEmployee.getDirectReports().add(createEmployeeTree(employee));
      }
      return currentEmployee;
    }
  }

  private List<Employee> attachPropertiesToEmployees(List<Employee> employees) {
    List<Employee> employeesWithProperties = new ArrayList<>();
    for (Employee employee : employees) {
      List<Map<String, Object>> employeeProperties = employeeRepository.getEmployeeProperties(employee.getId());
      employeesWithProperties.add(attachPropertiesToEmployee(employee, employeeProperties));
    }
    return employeesWithProperties;
  }

  private Employee attachPropertiesToEmployee(Employee employee, List<Map<String, Object>> properties) {
    for (Map<String, Object> property : properties) {
      employee.getProperties().put((String)property.get("KEY"), (String)property.get("VALUE"));
    }
    return employee;
  }
}
