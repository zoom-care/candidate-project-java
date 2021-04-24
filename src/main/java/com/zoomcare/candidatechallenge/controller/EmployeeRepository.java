package com.zoomcare.candidatechallenge.controller;

import org.springframework.data.repository.CrudRepository;

import com.zoomcare.candidatechallenge.model.Employee;


// class name, primary key
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	
	// use call crud operations here
}
