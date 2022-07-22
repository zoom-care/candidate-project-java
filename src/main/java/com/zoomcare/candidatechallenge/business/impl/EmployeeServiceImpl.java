package com.zoomcare.candidatechallenge.business.impl;

import com.zoomcare.candidatechallenge.business.EmployeeService;
import com.zoomcare.candidatechallenge.business.exception.DataException;
import com.zoomcare.candidatechallenge.db.model.Employee;
import com.zoomcare.candidatechallenge.db.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public List<Employee> findAllEmployees() throws DataException {
    return employeeRepository.findAll().orElseThrow(DataException::new);
  }

  @Override
  public Employee findByEmployeeId(String employeeId) throws DataException {
    return employeeRepository.findByEmployeeId(employeeId).orElseThrow(DataException::new);
  }
}
