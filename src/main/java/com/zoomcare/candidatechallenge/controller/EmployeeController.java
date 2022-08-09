package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.NotFoundException;
import com.zoomcare.candidatechallenge.controller.dto.EmployeeResponseDTO;
import com.zoomcare.candidatechallenge.controller.dto.PropertyResponseDTO;
import com.zoomcare.candidatechallenge.data.entity.Employee;
import com.zoomcare.candidatechallenge.data.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.mapper.EmployeeToEmployeeResponseMapper;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<EmployeeResponseDTO> employees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) throws NotFoundException {
        return employeeService.getById(id);
    }

}
