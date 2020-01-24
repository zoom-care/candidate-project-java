package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long id);

    List<Employee> getEmployeesBySupervisorId(Long supervisor_id);

}
