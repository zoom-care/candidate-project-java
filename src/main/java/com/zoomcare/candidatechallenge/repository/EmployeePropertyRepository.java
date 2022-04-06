package com.zoomcare.candidatechallenge.repository;


import com.zoomcare.candidatechallenge.model.EmployeeProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePropertyRepository extends JpaRepository<EmployeeProperty, Long> {
}
