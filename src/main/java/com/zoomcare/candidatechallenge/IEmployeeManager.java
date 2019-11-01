package com.zoomcare.candidatechallenge;

import com.zoomcare.candidatechallenge.entities.Employee;

import java.util.List;

/**
 * This defines the operations to obtain the necessary information for an employee.
 *
 * @author Allen Wickham
 */
public interface IEmployeeManager
{
    /**
     * Obtains a list of all employees.
     *
     * @return a list of all employees. Will never be {@code null}.
     */
    List<Employee> getAllEmployees();

    /**
     * Obtains an individual employee.
     *
     * @param employeeId The ID of the employee. Must not be {@code null}.
     *
     * @return an individual employee.
     */
    Employee getEmployee(Long employeeId);
}
