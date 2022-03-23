package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PropertyRepository extends CrudRepository<Property, BigInteger> {
}
