package com.zoomcare.candidatechallenge.model;

/*
 * Structure for the restful output of the employee/properties table. this is loosely mapped, as the tables are recursively combined to generate the json.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDTO {
	private Long id;
	private List<EmployeeDTO> directReports;
	private Map<String, String> properties;
	
	public EmployeeDTO() {
		directReports = new ArrayList<EmployeeDTO> ();
		properties = new HashMap<String, String>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<EmployeeDTO> getDirectReports() {
		return directReports;
	}

	public void setDirectReports(List<EmployeeDTO> directReports) {
		this.directReports = directReports;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
