package com.zoomcare.candidatechallenge.services;

import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String getEmployeeById(Long id) {
        return "string";
    }
}
