package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.entity.EmployeeEntity;
import com.zoomcare.candidatechallenge.model.EmployeeModel;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService service;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id){
        EmployeeModel model = service.getEmployee(id);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeModel>> getTopEmployees(){
        List<EmployeeModel> topEmployees = service.getTopEmployees();
        return new ResponseEntity<>(topEmployees, HttpStatus.OK);
    }
}
