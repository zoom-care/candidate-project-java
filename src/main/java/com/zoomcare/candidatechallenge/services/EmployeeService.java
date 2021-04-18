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
      return currentEmployee;
    } else {
      for (Employee employee : directReports) {
        // recursively add expanded employees to create the employee hierarchy
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
      String currentKey = "";
      String currentValue = "";
      for (Map.Entry<String, Object> entry : property.entrySet()) {
        if (entry.getKey().equals("KEY")) {
          currentKey = (String)entry.getValue();
        }
        if (entry.getKey().equals("VALUE")) {
          currentValue = (String)entry.getValue();
        }
      }
      employee.getProperties().put(currentKey, currentValue);
    }
    return employee;
  }
}
