package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.EmployeeWithReports;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeController {

    /**
     * Returns a list of all top-level employees.
     *
     * @return the list of employees
     */
    ResponseEntity<List<EmployeeWithReports>> getAll();

    /**
     * Returns the employee with the given ID or a 404 response if no such employee exists.
     *
     * @param id the employee ID
     * @return the employee
     */
    ResponseEntity<EmployeeWithReports> getById(Long id);
}
