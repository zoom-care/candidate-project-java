package com.zoomcare.candidatechallenge.models.repository;

import com.zoomcare.candidatechallenge.models.entity.Property;
import com.zoomcare.candidatechallenge.models.entity.PropertyPKId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Property, PropertyPKId> {
    public List<Property> findByEmployeeId(Long employeeId);
}
