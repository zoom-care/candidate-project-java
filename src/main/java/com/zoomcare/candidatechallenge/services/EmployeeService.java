package com.zoomcare.candidatechallenge.services;

import com.zoomcare.candidatechallenge.Models.Employee;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
}

/*
Do want:
tests
 */

/*
need:
get all top-level employees

all employees returned include id, all properties, and a nested list of direct reports
 */