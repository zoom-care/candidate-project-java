package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
