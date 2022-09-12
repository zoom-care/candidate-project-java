package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.output.EmployeeDTO;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllTop")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EmployeeDTO>> getTopLevelEmployees(){
        List<EmployeeDTO> employeesDTO = employeeService.getAllTopLevelEmployees();

        return ResponseEntity.ok(employeesDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeDTO> getById(@PathVariable Integer id) throws NotFoundException {
        EmployeeDTO employeeDTO = employeeService.getById(id);

        return ResponseEntity.ok(employeeDTO);
    }
}
