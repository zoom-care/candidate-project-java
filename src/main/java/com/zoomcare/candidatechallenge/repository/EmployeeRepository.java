package com.zoomcare.candidatechallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.model.Employee;

/**
 * Dao is a simple interface for any data abstraction object
 * The generic param M lets you use whatever model you like
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}