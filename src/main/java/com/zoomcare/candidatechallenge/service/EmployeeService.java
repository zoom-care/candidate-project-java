package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse getEmployee(Long id);
    List<EmployeeResponse> getEmployees();
}
