package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(Long id);
    List<Employee> getEmployees();
}
