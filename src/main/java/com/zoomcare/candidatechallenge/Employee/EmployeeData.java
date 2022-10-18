package com.zoomcare.candidatechallenge.Employee;

import java.util.List;
import java.util.Optional;

import com.zoomcare.candidatechallenge.Employee.entities.Employee;
import com.zoomcare.candidatechallenge.Employee.entities.Property;

public class EmployeeData {
    private final Optional<Employee> employee;
    private final List<Property> property;
 
    public EmployeeData(Optional<Employee> employee, List<Property> property) {
      this.employee = employee;
      this.property = property;
    }
 
    public Optional<Employee> getEmployee() { return employee; }
    public List<Property> getProperty() { return property; }  
}