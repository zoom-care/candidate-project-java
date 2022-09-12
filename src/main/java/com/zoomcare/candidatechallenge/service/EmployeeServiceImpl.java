package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllSupervisors() {
        return employeeRepository.findAllSupervisors();
    }

    @Override
    public List<Employee> findBySupervisorId(Integer supervisor_id) {
        return employeeRepository.findBySupervisorId(supervisor_id);
    }
}
