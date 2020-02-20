package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getTopLevel() {
        return this.employeeRepository.findBySupervisorIdIsNull();
    }

    public Employee getEmployee(final Long id)
            throws EmployeeNotFoundException {
        return this.employeeRepository
                .findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
    }
}
