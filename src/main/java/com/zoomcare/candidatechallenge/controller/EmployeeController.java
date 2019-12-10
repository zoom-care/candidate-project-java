package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exception.ResourceNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService eServ;


    @RequestMapping("/employees")
    public List<Employee> getTopLevelEmployees() {
        return eServ.getTopLevelList();
    }

    @RequestMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") String id) {

        Employee theEmp =  eServ.getById(new BigInteger(id));
        if (theEmp == null) throw new ResourceNotFoundException();
        return theEmp;
    }

}
