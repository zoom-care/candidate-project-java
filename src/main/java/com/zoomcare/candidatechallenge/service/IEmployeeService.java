package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;

import java.math.BigInteger;
import java.util.List;

public interface IEmployeeService {
    Employee getEmployeeById(BigInteger employeeId);
    List<Employee> getTopLevelEmployees();
}
