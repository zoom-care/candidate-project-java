package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
