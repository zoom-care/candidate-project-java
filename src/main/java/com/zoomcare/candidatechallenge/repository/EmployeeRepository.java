package com.zoomcare.candidatechallenge.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	// Assuming top level employees are the ones that do not have supervisor
	List<Employee> findBySupervisorIdIsNull();
}