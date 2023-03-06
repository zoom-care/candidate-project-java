package com.zoomcare.candidatechallenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findAll();

	Optional<Employee> findById(Long id);

	List<Employee> findAllBySupervisorIdIsNull();

}
