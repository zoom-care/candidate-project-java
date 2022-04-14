package com.zoomcare.candidatechallenge.models.services;

import java.util.List;

import com.zoomcare.candidatechallenge.models.entity.Employee;
import com.zoomcare.candidatechallenge.models.entity.Property;


public interface IPropertyService {
	public List<Property> findAll();
	
	public Property save(Property property);
	
	public Property findById(Long id);
	
	public List<Property> findByEmployeeId(Long employeeId);
	
	public void delete(Long id);
}
