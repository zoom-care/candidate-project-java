package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getTopLevelEmployees() {
        return employeeRepository.findBySupervisorIdIsNull();
    }

    public Employee getEmployee(Integer employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.orElseThrow(() -> new EntityNotFoundException(String.format("Employee %1d not found", employeeId)));
    }
}
