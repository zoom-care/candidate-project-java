package com.zoomcare.candidatechallenge.controller;

/*
 * EmployeeController
 * this is the controller for the employees endpoint. handles all operations, but returns not implemented for put, post and delete.
 * 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.model.EmployeeDTO;
import com.zoomcare.candidatechallenge.service.IServiceEmployee;

import org.springframework.http.HttpStatus;

@RestController
public class EmployeeController {
	@Autowired
	IServiceEmployee service;
	
    @RequestMapping("/employees")
    public List<EmployeeDTO> findAll() {
        return service.findAll();
    }
	
    @GetMapping(value = "/employees/{id}")
    public EmployeeDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
 
    @PostMapping
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Long create(@RequestBody EmployeeDTO resource) {
        return service.create(resource);
    }
 
    @PutMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void update(@PathVariable( "id" ) Long id, @RequestBody EmployeeDTO resource) {
        service.update(resource);
    }
 
    @DeleteMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
 
}
