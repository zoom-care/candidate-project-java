package com.zoomcare.candidatechallenge.data.repository;

import com.zoomcare.candidatechallenge.data.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
  
  List<EmployeeEntity> findAllBySupervisorIdIsNull();
}
