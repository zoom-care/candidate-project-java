package com.zoomcare.candidatechallenge.repositories;

import java.util.List;
import java.util.Map;

import com.zoomcare.candidatechallenge.models.Employee;

public interface IEmployeeRepository {
  public List<Employee> getAllSupervisors();
  public List<Employee> getEmployeesBySupervisorId(Long id);
  public Employee getEmployee(Long id);
  public List<Map<String, Object>> getEmployeeProperties(Long id);
}
