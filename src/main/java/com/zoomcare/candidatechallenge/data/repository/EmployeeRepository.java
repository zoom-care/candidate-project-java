package com.zoomcare.candidatechallenge.data.repository;

import com.zoomcare.candidatechallenge.data.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
