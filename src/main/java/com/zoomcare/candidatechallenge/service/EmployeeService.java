package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto getEmployee(Long id);
    List<EmployeeDto> getTopLevelEmployeeList();
}
