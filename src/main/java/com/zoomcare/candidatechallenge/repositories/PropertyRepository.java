package com.zoomcare.candidatechallenge.repositories;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomcare.candidatechallenge.entities.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, BigInteger>{
	
	Optional<Property> findByEmployeeId(BigInteger employeeId);
	
	Optional<List<Property>> findAllByEmployeeId(BigInteger employeeId);

}
