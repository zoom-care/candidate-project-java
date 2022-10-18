package com.zoomcare.candidatechallenge.Employee.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zoomcare.candidatechallenge.Employee.entities.Property;

public interface PropertyRepository extends CrudRepository<Property, Integer> {

    @Query("select u from Property u where u.id = :id")
    List<Property> findByEmployeeId(@Param("id") Integer id);

}
