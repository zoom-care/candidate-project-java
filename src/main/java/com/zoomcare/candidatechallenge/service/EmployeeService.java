package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

}
