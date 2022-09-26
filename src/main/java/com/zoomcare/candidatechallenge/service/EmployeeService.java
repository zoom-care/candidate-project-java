package com.zoomcare.candidatechallenge.service;


import com.zoomcare.candidatechallenge.exception.BusinessException;
import com.zoomcare.candidatechallenge.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllTopLevelEmployees() throws BusinessException;

    public Employee findById(Long id) throws BusinessException;
}
