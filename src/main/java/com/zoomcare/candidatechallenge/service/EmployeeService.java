package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findAllTopLevelEmployees();

    List<EmployeeDTO> findEmployeeById(Integer id);
}
