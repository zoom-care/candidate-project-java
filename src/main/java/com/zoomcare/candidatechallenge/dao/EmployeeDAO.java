package com.zoomcare.candidatechallenge.dao;

import org.springframework.data.repository.CrudRepository;

import com.zoomcare.candidatechallenge.model.Employee;

/**
 * DAO for employee
 */
public interface EmployeeDAO extends CrudRepository<Employee, Long> {

}
