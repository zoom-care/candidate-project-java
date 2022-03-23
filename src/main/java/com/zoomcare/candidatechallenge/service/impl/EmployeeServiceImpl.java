package com.zoomcare.candidatechallenge.service.impl;

import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.IEmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(BigInteger employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(!employee.isPresent()) {
            log.error("Employee id: {} not found", employeeId);
            throw new EmployeeNotFoundException(String.format("Employee id: %s not found", employeeId));
        }
        return employee.get();
    }

    @Override
    public List<Employee> getTopLevelEmployees() {
        return employeeRepository.getTopLevelEmployees();
    }
}
