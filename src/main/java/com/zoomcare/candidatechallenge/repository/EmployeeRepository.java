package com.zoomcare.candidatechallenge.repository;

import com.example.zoomcarecodechallenge.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySupervisorIsNull();

}
