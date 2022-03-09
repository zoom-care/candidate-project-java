package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee create(final Employee employee);
    Employee getById(final Long id);
    List<Employee> getEmployees();
    void deleteById(final Long id);

}
