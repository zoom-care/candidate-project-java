package com.zoomcare.candidatechallenge.service.impl;

import com.zoomcare.candidatechallenge.model.dto.EmployeePropertyDto;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<EmployeePropertyDto> getEmployeeById(Long employeeId) {
    return employeeRepository.getEmployeeById(employeeId);
  }

  public List<EmployeePropertyDto> getTopLevelEmployees() {
    return employeeRepository.getTopLevelEmployees();
  }
}
