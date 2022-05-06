package com.zoomcare.candidatechallenge.services;

import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getEmployees() {
        return employeeRepo.getEmployees();
    }
}
