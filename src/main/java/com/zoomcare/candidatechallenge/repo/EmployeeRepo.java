package com.zoomcare.candidatechallenge.repo;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepo extends JpaRepository<Employee, Integer> {


}