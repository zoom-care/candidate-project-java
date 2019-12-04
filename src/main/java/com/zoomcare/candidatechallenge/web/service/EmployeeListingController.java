package com.zoomcare.candidatechallenge.web.service;

import com.zoomcare.candidatechallenge.business.service.EmployeeService;
import com.zoomcare.candidatechallenge.data.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api")
public class EmployeeListingController {


    @Autowired
    private EmployeeService employeeService;


    @RequestMapping(method = RequestMethod.GET, value = "/employees/{id}")
    public Employee getDirectReportsForEmployeeByEmployeeId(@PathVariable(value = "id") Long id)
    {
        return this.employeeService.getEmployeeById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employees/")
    public Employee getDirectReportsForTopEmployee()
    {
        return this.employeeService.getEmployeeById(1L);
    }

}
