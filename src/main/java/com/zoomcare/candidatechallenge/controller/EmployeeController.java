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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.dao.EmployeeDao;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // TODO: add service?
    // for now let's just use a DAO - Service has extra logic like analytics, reporting, transactions, and security related things
    // also, who doesn't love Autowired? (Yum dependency injection)
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * Return an employee by its given id
     * @param employee_id an employees id
     * @return an employee by id
     */
    @GetMapping("{employee_id:[0-9]+}")
    @ResponseBody
    public ResponseEntity<Employee> getbyId(@PathVariable("employee_id") Long employeeId) {
        Employee e = employeeDao.get(employeeId);
        logger.info("Received employee_id {}", employeeId);
        return (e!=null) ? ResponseEntity.ok().body(e) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Creates an employee and returns the id
     * @param Employee a request object containing the employees supervisor id and the list of properties (key-value pairs) to associate with the employee
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> create(@RequestBody Employee resource) {
        Employee e = employeeDao.save(resource);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{employeeId}").buildAndExpand(e.getId()).toUri();
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