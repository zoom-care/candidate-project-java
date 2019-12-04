package com.zoomcare.candidatechallenge.business.service;

import com.zoomcare.candidatechallenge.data.model.Employee;
import com.zoomcare.candidatechallenge.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public List<Employee> getAllEmployeesForSupervisor(Long supervisor_Id){
        return employeeRepo.findBySupervisor_Id(supervisor_Id);
    }

    public Employee getEmployeeById(Long employeeId){
        return employeeRepo.findEmployeeById(employeeId);
    }

}
