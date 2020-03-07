package com.zoomcare.candidatechallenge;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    /**
     * Saves an employee to the database
     * 
     * @param employee
     */
    public void save(final Employee employee) {
        System.out.println("EmployeeService.save");
        repository.save(employee);
    }

    /**
     * @return All employees in the database
     */
    public List<Employee> getAll() {
        System.out.println("EmployeeService.getAll");
        final List<Employee> employees = new ArrayList<>();
        repository.findAll().forEach(employee -> employees.add(employee));
        return employees;
    }
}