package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.repository.Employee;
import com.zoomcare.candidatechallenge.repository.Property;
import com.zoomcare.candidatechallenge.repository.PropertyId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface PropertyDAO extends JpaRepository<Property, PropertyId> {

    Optional<Employee> findById(BigInteger employeeID);
}
