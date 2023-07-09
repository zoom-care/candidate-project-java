package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    ResponseEntity<EmployeeDTO> getById(@PathVariable Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.findById(id);
        if (!employeeDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO.get());
    }

    @GetMapping("/topLevel")
    ResponseEntity<List<EmployeeDTO>> getTopLevelEmployees() {
        List<EmployeeDTO> employeeDTOs = employeeService.getTopLevelEmployee();
        if (employeeDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTOs);
    }
}
