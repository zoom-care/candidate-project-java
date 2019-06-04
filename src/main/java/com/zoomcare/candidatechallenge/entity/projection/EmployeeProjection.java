package com.zoomcare.candidatechallenge.entity.projection;

import java.util.Map;
import java.util.Set;

public interface EmployeeProjection {
	
	Long getId();
	
	Map<String, String> getProperties();
	
	Set<EmployeeProjection> getEmployees();
}
