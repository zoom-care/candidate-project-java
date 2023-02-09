package com.zoomcare.candidatechallenge.services;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.models.Property;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;
import com.zoomcare.candidatechallenge.repositories.PropertyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles employee information requests.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PropertyRepository propertyRepository) {
        this.employeeRepository = employeeRepository;
        this.propertyRepository = propertyRepository;
    }

    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return null;
        }

        List<Property> properties = propertyRepository.findByEmployeeId(id).orElse(Collections.emptyList());
        employee.setProperties(properties);

        List<Employee> directReports = employeeRepository.findDirectReports(id).orElse(Collections.emptyList());
        employee.setDirectReports(directReports);

        List<Employee> employees = new ArrayList<>(directReports);
        while (!employees.isEmpty()) {
            Employee currentEmployee = employees.remove(0);
            List<Property> currentProperties = propertyRepository.findByEmployeeId(currentEmployee.getId()).orElse(Collections.emptyList());
            currentEmployee.setProperties(currentProperties);
            directReports = employeeRepository.findDirectReports(currentEmployee.getId()).orElse(Collections.emptyList());
            currentEmployee.setDirectReports(directReports);
            employees.addAll(directReports);
        }

        return employee;
    }

    public List<Employee> getAllTopLevelEmployees() {
        List<Employee> topLevelEmployees = employeeRepository.findBySupervisorIdIsNull().orElse(Collections.emptyList());
        return topLevelEmployees.stream()
                .map(employee -> getEmployeeById(employee.getId()))
                .collect(Collectors.toList());
    }
}