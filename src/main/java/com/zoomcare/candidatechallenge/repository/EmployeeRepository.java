package com.zoomcare.candidatechallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.model.EmployeeEntity;

@SuppressWarnings("unused")
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	
}
