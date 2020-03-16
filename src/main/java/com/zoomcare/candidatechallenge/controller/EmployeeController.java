package com.zoomcare.candidatechallenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoomcare.candidatechallenge.exception.NotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.model.dto.Node;
import com.zoomcare.candidatechallenge.model.dto.PropertyDTO;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController
{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable long id) {
        final Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            final ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            return ResponseEntity.ok(modelMapper.map(employee.get(), EmployeeDTO.class));
        }
        throw new NotFoundException("Employee NOT FOUND | ID: " + id);
    }

    @GetMapping("/employees/top-level")
    public ResponseEntity<List<EmployeeDTO>> findAllTopLevelEmployees() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        final List<Employee> allTopLevelEmployees = employeeRepository.findAllTopLevelEmployees();
        final List<EmployeeDTO> employeeDTOs = allTopLevelEmployees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(employeeDTOs);
    }
}
