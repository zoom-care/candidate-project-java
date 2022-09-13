package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeRepository {

    List<EmployeeDTO> findAllTopLevelEmployees();

    List<EmployeeDTO> findEmployeeById(Integer id);
}
