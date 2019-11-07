package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.dto.EmployeePropertyDto;
import java.util.List;

public interface EmployeeService {

  List<EmployeePropertyDto> getTopLevelEmployees();
  List<EmployeePropertyDto> getEmployeeById(Long employeeId);
}
