package com.zoomcare.candidatechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

   @Autowired
   private EmployeeService employeeService;

   @GetMapping("/all")
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
   }


    @GetMapping("/{id}")
    public EmployeeEntity getById(@PathVariable(name = "id") Long id){
        return  employeeService.getById(id);
    }
}
