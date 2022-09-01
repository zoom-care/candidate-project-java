package com.zoomcare.candidatechallenge.services;

import com.zoomcare.candidatechallenge.Models.Employee;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

}

/*
Do want:
tests
 */

/*
need:
get all top-level employees
get an employee by id

all employees returned include id, all properties, and a nested list of direct reports
 */