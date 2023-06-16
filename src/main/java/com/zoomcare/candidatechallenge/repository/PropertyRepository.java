package com.zoomcare.candidatechallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Employee> {

}
