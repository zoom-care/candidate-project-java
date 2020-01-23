package com.zoomcare.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.zoomcare.model.Employee;
import com.zoomcare.dao.EmployeeDao;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    // TODO: add service?
    // for now let's just use a DAO - Service has extra logic like analytics, reporting, and security related things
    // also, who doesn't love Autowired? (Yum dependency injection)
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * Return an employee by its given id
     * @param id an employees id
     * @return an employee by id
     */
    @GetMapping("/{id}")
    public Employee getbyId(@PathVariable("id") Long id) {
        return employeeDao.get(id);
    }

    /**
     * Creates an employee and returns the id
     * @param Employee a request object containing the employees supervisor id and the list of properties (key-value pairs) to associate with the employee
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Employee resource) {
        return null;
    }

    /**
     * Return all employees
     * @return collection of Employees.
     */
    @GetMapping
    public Collection<Employee> getAll() {
        return employeeDao.getAll();
    }
}