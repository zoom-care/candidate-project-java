package com.zoomcare.candidatechallenge.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/{id}")
    public List<Employee> getEmployeeById (@PathVariable final long id){        
        return employeeRepository.getEmployeeById(id);
    }

    @RequestMapping("/employees")
    public List<Employee> getTopLevelEmployees (){
        return employeeRepository.getTopLevelEmployees();
    }
}
