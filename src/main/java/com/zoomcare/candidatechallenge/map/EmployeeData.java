package com.zoomcare.candidatechallenge.map;

import java.util.List;

import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.entities.Property;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeData {
	
	private Employee employee;
	
	private List<Property> properties;
	
}
