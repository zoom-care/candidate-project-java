package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeesInfoController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hello")
    //@RequestMapping(value="/hello", method= RequestMethod.GET)
    public String helloWorld(){
        return "Hello World !!";
    }

    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getEmployee(){
        List<Employee> list = employeeService.findAll();
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value="{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Integer id){
        Optional<Employee> employee = employeeService.findById(id);
        if(!employee.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employee.get());
    }
}
