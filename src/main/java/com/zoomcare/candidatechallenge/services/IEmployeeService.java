package com.zoomcare.candidatechallenge.services;

import java.util.List;

import com.zoomcare.candidatechallenge.models.Employee;

public interface IEmployeeService {
  public List<Employee> getAllEmployees();
  public List<Employee> getEmployeeById(Long id);
}
