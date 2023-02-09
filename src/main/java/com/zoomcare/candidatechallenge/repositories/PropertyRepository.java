package com.zoomcare.candidatechallenge.repositories;

import com.zoomcare.candidatechallenge.models.Property;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Access layer for the property table.
 */
public interface PropertyRepository extends CrudRepository<Property, Long> {
    @Query("SELECT * FROM Property WHERE employee_id = :employeeId")
    Optional<List<Property>> findByEmployeeId(Long employeeId);
}