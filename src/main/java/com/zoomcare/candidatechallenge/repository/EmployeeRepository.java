package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findBySupervisorIsNull();
}
