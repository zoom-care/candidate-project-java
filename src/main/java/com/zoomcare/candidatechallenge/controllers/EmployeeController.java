package com.zoomcare.candidatechallenge.controllers;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoints for obtaining information about an employee or collections of employees.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves the employee with the given ID.
     *
     * @param id the ID of the employee to retrieve
     * @return a {@code ResponseEntity} representing the requested employee. The entity will contain the
     *         employee data, or a {@code NOT_FOUND} status if an employee with the given ID could not be found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Retrieves all top-level employees (employees with no managers).
     *
     * @return a {@code ResponseEntity} representing the collection of top-level employees. The entity will contain
     *         the employee data.
     */
    @GetMapping("/top-level-employees")
    public ResponseEntity<List<Employee>> getAllTopLevelEmployees() {
        List<Employee> topLevelEmployees = employeeService.getAllTopLevelEmployees();
        return new ResponseEntity<>(topLevelEmployees, HttpStatus.OK);
    }
}