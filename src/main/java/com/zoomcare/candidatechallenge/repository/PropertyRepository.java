package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.model.PropertyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, PropertyId> {
}
