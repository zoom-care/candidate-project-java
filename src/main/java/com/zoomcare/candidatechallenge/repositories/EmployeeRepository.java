package com.zoomcare.candidatechallenge.repositories;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, BigInteger>{
	
	Optional<Employee> findById(BigInteger id);
	
	Optional<List<Employee>> findAllBySupervisoridIsNull();
}
