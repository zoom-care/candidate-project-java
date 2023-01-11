
package com.zoomcare.candidatechallenge.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.services.EmployeeService;

@RestController
public class EmployeeController {

    private EmployeeService empServ;
    
    @Autowired
    public EmployeeController(EmployeeService empServ) {
        this.empServ = empServ;
    }


    
    @GetMapping("/list")
    public String listAllEmployees() {
        return empServ.listAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathParam("id") String id) {
        
        try {
            return empServ.getEmployeeById(id); 
        } catch (RuntimeException e) {
            return "Error: Invalid Employee ID";
        }
    }
}
