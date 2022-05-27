package com.zoomcare.candidatechallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoomcare.candidatechallenge.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long>{

}
