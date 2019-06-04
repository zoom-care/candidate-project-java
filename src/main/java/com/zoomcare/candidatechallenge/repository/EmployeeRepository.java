package com.zoomcare.candidatechallenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.entity.projection.EmployeeProjection;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	public List<EmployeeProjection> findBySupervisorIsNull();
	
	public Optional<EmployeeProjection> getById(Long id);
}
