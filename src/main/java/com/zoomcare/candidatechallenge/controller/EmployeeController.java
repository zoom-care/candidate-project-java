package com.zoomcare.candidatechallenge.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.dao.EmployeeDao;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    // TODO: add service?
    // for now let's just use a DAO - Service has extra logic like analytics, reporting, transactions, and security related things
    // also, who doesn't love Autowired? (Yum dependency injection)
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * Return an employee by its given id
     * @param id an employees id
     * @return an employee by id
     */
    @GetMapping(path="/{id}", produces="application/json")
    @ResponseBody
    public ResponseEntity<Employee> getbyId(@PathVariable("id") Long id) {
        Employee e = employeeDao.get(id);
        return (e==null) ? ResponseEntity.ok().body(e) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Creates an employee and returns the id
     * @param Employee a request object containing the employees supervisor id and the list of properties (key-value pairs) to associate with the employee
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> create(@RequestBody Employee resource) {
        Employee e = employeeDao.save(resource);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();
        return ResponseEntity.created(location).body(e);
    }

    /**
     * Return all employees
     * @return collection of Employees.
     */
    @GetMapping(produces="application/json")
    @ResponseBody
    public Collection<Employee> getAll() {
        return employeeDao.getAll();
    }
}