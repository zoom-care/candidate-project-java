package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findById(final Integer employeeId);

    List<Employee> getReports(final Integer superVisorId);

    List<Employee> findAll();

}
