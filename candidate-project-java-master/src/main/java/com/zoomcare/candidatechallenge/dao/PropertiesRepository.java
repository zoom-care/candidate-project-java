package com.zoomcare.candidatechallenge.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.models.hibernate.Property;

@Repository
public interface PropertiesRepository extends JpaRepository<Property, Integer> {
    
    Optional<List<Property>> findAllByEmployeeId(long employeeId);
}
