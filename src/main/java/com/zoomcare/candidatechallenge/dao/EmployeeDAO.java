package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.repository.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface EmployeeDAO extends JpaRepository<Employee,Integer> {

    Optional<Employee> findById(BigInteger employeeID);
}
