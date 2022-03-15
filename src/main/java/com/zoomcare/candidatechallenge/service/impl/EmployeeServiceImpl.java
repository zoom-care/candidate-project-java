package com.zoomcare.candidatechallenge.service.impl;

import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        List<Employee> employeeReports = employeeRepository.getReports(employee.get().getId());
        for(Employee e : employeeReports){
            e.setReports(employeeRepository.getReports(e.getId()));
        }
        employee.get().setReports(employeeReports);
        return employee;
    }

    @Override
    public List<Employee> getReports(Integer superVisorId) {
        return employeeRepository.getReports(superVisorId);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList= employeeRepository.findAll();
        for(Employee e : employeeList){
            e.setReports(employeeRepository.getReports(e.getId()));
        }
        return employeeList;
    }

}
