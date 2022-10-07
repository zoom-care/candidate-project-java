package com.zoomcare.candidatechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity getById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
       // List<>

        return employeeRepository.findById(id).get();
    }
}
