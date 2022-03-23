package com.zoomcare.candidatechallenge.api.impl;

import com.zoomcare.candidatechallenge.adapters.EmployeeAdapter;
import com.zoomcare.candidatechallenge.api.IEmployeeController;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/zoom-care")
@CrossOrigin("*")
@AllArgsConstructor
public class EmployeeControllerImpl implements IEmployeeController {

    private final IEmployeeService employeeService;

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeService.getTopLevelEmployees();
        List<EmployeeDto> employeesDto = EmployeeAdapter.map(employees);
        return employeesDto;
    }

    @Override
    public EmployeeDto getEmployee(BigInteger id) {
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeDto dto = EmployeeAdapter.map(employee);
        return dto;
    }
}
