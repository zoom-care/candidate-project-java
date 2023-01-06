package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.entity.Property;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.exception.PropertyNotFoundException;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.repository.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CrudController {
    // ,  ;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PropertiesRepository propertiesRepository;

    // Employee
    @PostMapping("/api/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        Employee _employee = employeeRepository.save(new Employee(employee.getSupervisor_id()));

        return new ResponseEntity<>(_employee, HttpStatus.CREATED);
    }

    @GetMapping("/api/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> lemployees = new ArrayList<Employee>();
        employeeRepository.findAll().forEach(lemployees::add);

        if (lemployees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lemployees, HttpStatus.OK);
    }

    @GetMapping("/api/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Book with Id = " + id + " not found");
        }

        return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
    }

    @PutMapping("/api/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {

        Optional<Employee> _employee = employeeRepository.findById(id);

        if(!_employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with Id = " + id + " not found.");
        }

        _employee.get().setSupervisor_id(employee.getSupervisor_id());

        return new ResponseEntity<>(employeeRepository.save(_employee.get()), HttpStatus.OK);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {

        Optional employee = employeeRepository.findById(id);

        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee id = " + id + " not found.");
        }

        employeeRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Properties
    @PostMapping("/api/employees/{employeeId}/properties")
    public ResponseEntity<Property> createProperty(@PathVariable(value = "employeeId") Long employeeId, @RequestBody Property properties) {

        Property _properties = employeeRepository.findById(employeeId).map(emp -> { // Ojo con el map
            properties.setEmployee(emp);
            return propertiesRepository.save(properties);
        }).orElseThrow(() -> new EmployeeNotFoundException("Not found Book with id = " + employeeId));

        return new ResponseEntity<>(_properties, HttpStatus.CREATED);
    }

    @GetMapping("/api/properties/{id}")
    public ResponseEntity<Property> getPropertiesById(@PathVariable(value = "id") Long id) {

        Property _properties = propertiesRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id = " + id));

        return new ResponseEntity<>(_properties, HttpStatus.OK);
    }
}
