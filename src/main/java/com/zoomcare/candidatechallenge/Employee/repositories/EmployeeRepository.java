package com.zoomcare.candidatechallenge.Employee.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zoomcare.candidatechallenge.Employee.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
