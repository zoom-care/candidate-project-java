package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.exception.ResourceNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
//import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;


@RestController
public class EmployeeController {
//    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
//        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

//    @GetMapping(produces = "application/json", path = "/toplevelemployees")
//    public ResponseEntity<Object> getTopLevelEmployees() {
//        List<Employee> employees = employeeRepository.findAll();
//        return new ResponseEntity<>(employees, HttpStatus.OK);
//    }
//
//    @GetMapping(produces = "application/json", path = "/employeebyid/{id}")
//    public ResponseEntity<Object> getEmployeesByID(@PathVariable("id") BigInteger ID) throws ResourceNotFoundException {
//            Employee employee = employeeRepository.findById(ID)
//                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + ID));
//            return ResponseEntity.ok().body(employee);
//    }

    // Proof of concept
    @GetMapping("/test")
    @ResponseBody
    public List<EmployeeDTO> getAllEmployees() {
        List <EmployeeDTO> employees = employeeService.getAllEmployees();
        return employees;
    }

}
