package com.zoomcare.candidatechallenge.controller;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = {"", "/toplevel"}, method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.topEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
