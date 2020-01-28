package com.zoomcare.candidatechallenge.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

/**
 * EmployeeController handles REST requests related to Employees in the system
 * @author Christian Bernard
 * @version 0.1
 * @since 2020-01-28
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // TODO: add service?
    // for now let's just use a DAO - Service has extra logic like analytics, reporting, transactions, and security related things
    // also, who doesn't love Autowired? (Yum dependency injection)
    @Autowired
    private EmployeeRepository repository;

    public EmployeeRepository getRepository() {
        return repository;
    }
 
    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Return an employee by its given id
     * @param employee_id an employees id
     * @return an employee by id
     */
    @GetMapping("{employee_id:[0-9]+}")
    @ResponseBody
    public ResponseEntity<Employee> getbyId(@PathVariable("employee_id") Long employeeId) {
        Optional<Employee> opt = repository.findById(employeeId);
        logger.info("Received employee_id {}", employeeId);
        Employee e = opt.orElse(null);
        if(e!=null) {
            return ResponseEntity.ok().body(e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Creates an employee and returns the id
     * @param Employee a request object containing the employees supervisor id and the list of properties (key-value pairs) to associate with the employee
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> create(@RequestBody Employee resource) {
        Employee e = repository.save(resource);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{employeeId}").buildAndExpand(e.getId()).toUri();
        return ResponseEntity.created(location).body(e);
    }

    /**
     * Return all employees
     * @return collection of Employees.
     */
    @GetMapping(produces="application/json")
    @ResponseBody
    public List<Employee> getAll() {
        return repository.findAll();
    }
}