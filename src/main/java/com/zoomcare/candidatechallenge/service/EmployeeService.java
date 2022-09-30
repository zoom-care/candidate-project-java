package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> topEmployees(){
        List<Employee> employeeList = employeeRepo.findAll();
        return employeeList;
    }

    public Employee getEmployeeById(Long id){
        Optional<Employee> employeeOptional = employeeRepo.findEmployeeById(id);
        if(employeeOptional.isPresent()){
            return employeeOptional.get();
        }
        return null;
    }
}
