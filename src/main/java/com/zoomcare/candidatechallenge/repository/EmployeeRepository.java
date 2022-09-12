package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findBySupervisorId(Integer supervisorId);
}
