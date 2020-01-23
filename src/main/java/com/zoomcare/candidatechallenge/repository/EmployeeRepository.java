package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findBySupervisorIdIsNull();
}
