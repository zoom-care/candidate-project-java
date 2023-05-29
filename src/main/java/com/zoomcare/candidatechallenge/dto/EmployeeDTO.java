package com.zoomcare.candidatechallenge.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * DTO to return employee
 */
@JsonInclude(Include.NON_NULL)
public abstract class EmployeeDTO {
	
	/** Employee ID */
	private Long id;
	
	/** Properties map **/
	private Map<String, String> properties;
	
	public EmployeeDTO() {
		properties = new HashMap<>();
	}
	
	public EmployeeDTO(Long id, Map<String, String> properties) {
		this.id = id;
		this.properties = properties;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
