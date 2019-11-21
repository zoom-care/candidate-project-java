package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exceptions.EmployeeInternalServerError;
import com.zoomcare.candidatechallenge.exceptions.EmployeeNotFound;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepositoryImpl employeeRepository;

    @GetMapping("/employees/{employee_id}")
    Employee employees(@PathVariable("employee_id") Long employeeId) throws EmployeeNotFound, EmployeeInternalServerError {
        log.info("Retrieving employee by id {}", employeeId);

        try {
            Employee employee = employeeRepository.getEmployeesByID(employeeId);

            if (employee == null) {
                throw new EmployeeNotFound( String.format("No entry was found for employee %s", employeeId));
            }
            return employee;
        } catch (Exception ex) {
            throw new EmployeeInternalServerError(String.format("Unexpected error occurred during request. %s", ex.getLocalizedMessage()));
        }
    }

    @GetMapping("/employees")
    List<Employee> employees() throws EmployeeInternalServerError {
        log.info("Retrieving all employees");

        try {
            List<Employee> employees = employeeRepository.findAll();

            log.info("Found {} employees", employees.size());
            return employees;
        } catch (Exception ex) {
            throw new EmployeeInternalServerError(String.format("Unexpected error occurred during request. %s", ex.getLocalizedMessage()));
        }
    }


}
