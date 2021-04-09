package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.model.PropertyID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, PropertyID> {
//    List<Property> findPropertiesByEMPLOYEEID(BigInteger employeeID);

}
