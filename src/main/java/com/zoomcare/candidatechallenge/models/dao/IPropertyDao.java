package com.zoomcare.candidatechallenge.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zoomcare.candidatechallenge.models.entity.Employee;
import com.zoomcare.candidatechallenge.models.entity.Property;


public interface IPropertyDao extends CrudRepository<Property, Long>{
	public List<Property> findByEmployeeId(Long employeeId);
}
