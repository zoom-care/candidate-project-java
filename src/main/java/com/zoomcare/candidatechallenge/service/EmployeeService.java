package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.EmployeeResponse;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<EmployeeResponse> getEmployee(Long id);

    List<EmployeeResponse> getEmployees();
}
