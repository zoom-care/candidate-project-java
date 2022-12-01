package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.EmployeeWithReports;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EmployeeService {

    /**
     * Returns all top-level employees.
     *
     * @return the list of employees
     */
    List<EmployeeWithReports> getAll();

    /**
     * Returns the employee with the given ID, or null if no such employee exists.
     *
     * @param id the employee ID
     * @return the employee
     */
    EmployeeWithReports getById(@PathVariable Long id);
}
