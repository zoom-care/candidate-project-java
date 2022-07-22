package com.zoomcare.candidatechallenge.business;

import com.zoomcare.candidatechallenge.business.exception.DataException;
import com.zoomcare.candidatechallenge.db.model.Employee;

import java.util.List;

public interface EmployeeService {
  List<Employee> findAllEmployees() throws DataException;
  Employee findByEmployeeId(String employeeId) throws DataException;
}
