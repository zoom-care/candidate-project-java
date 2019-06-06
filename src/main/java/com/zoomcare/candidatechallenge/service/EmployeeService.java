package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * Gets top level employees followed by nested direct reporting employees.
     *
     */
    public List<Employee> getEmployeesFromTop() {
        List<Employee> employees = new ArrayList<>();

        // Assumption made: Top guys don't have a boss. Look for supervisorId=null.
        employeeRepository.findBySupervisorIdIsNull().forEach(employees::add);

        return employees;
    }

    /**
     * Gets an employee by id followed by nested direct reporting employees.
     *
     */
    public Employee getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(()-> new EmployeeNotFoundException("Employee Not Found! id=" + id));
    }
}
