package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> findAllTopLevelEmployees() {
        return employeeRepository.findAllTopLevelEmployees();
    }

    @Override
    public List<EmployeeDTO> findEmployeeById(Integer id) {
        return employeeRepository.findEmployeeById(id);
    }
}
