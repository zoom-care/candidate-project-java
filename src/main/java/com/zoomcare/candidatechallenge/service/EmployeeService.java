package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.Employee;
import com.zoomcare.candidatechallenge.exceptions.EmployeeInternalServerError;
import com.zoomcare.candidatechallenge.exceptions.EmployeeNotFound;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees() throws EmployeeInternalServerError;
    Employee getEmployeeById(Long employeeId)  throws EmployeeNotFound, EmployeeInternalServerError;
}
