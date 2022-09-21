package com.zoomcare.candidatechallenge.repo;

import com.zoomcare.candidatechallenge.domain.Employee;

import java.util.List;

public interface EmployeeRepo {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
}
