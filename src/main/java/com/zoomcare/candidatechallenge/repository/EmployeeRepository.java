package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
