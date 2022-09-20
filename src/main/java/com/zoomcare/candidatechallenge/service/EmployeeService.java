package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.exceptions.NotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    Optional<Employee> getEmployeeById (Long employeeId) throws NotFoundException;

    List<Employee> getSupervisorById (Integer employeeId) throws NotFoundException;
}
