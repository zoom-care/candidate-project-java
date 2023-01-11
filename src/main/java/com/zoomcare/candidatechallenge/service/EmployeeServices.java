package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServices {

    EmployeeRepository employeeRepository;
    public EmployeeServices(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeebyId(Long id) {
        Employee dummy = new Employee();
        dummy.setId(189635L);
        return employeeRepository.findById(id).orElse(dummy);
    }
    public List<Employee> getTopLevelEmployee() {
        return employeeRepository.findAllBySupervisorIsNull();
    }

}
