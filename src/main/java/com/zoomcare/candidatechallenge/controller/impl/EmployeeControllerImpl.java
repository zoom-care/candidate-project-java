package com.zoomcare.candidatechallenge.controller.impl;

import com.zoomcare.candidatechallenge.controller.EmployeeController;
import com.zoomcare.candidatechallenge.exceptions.NotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api/v1")
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeControllerImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @Override
    public ResponseEntity<Optional<Employee>> getEmployeeById(Long employeeId) throws NotFoundException {

        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(employee);
    }

    @Override
    public ResponseEntity<List<Employee>> getSupervisorById(Integer supervisorId) throws NotFoundException {

        List<Employee> employeeBySupervisor = employeeService.getSupervisorById(supervisorId);

        return ResponseEntity.ok(employeeBySupervisor);
    }
}
