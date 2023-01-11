package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee ,Long > {
    List<Employee> findAllBySupervisorIsNull();
}
