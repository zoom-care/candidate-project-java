package com.zoomcare.candidatechallenge.dto;

import java.util.List;
import java.util.Map;

public class EmployeeDTO {

	private Long id;
	
	private Long supervisorId;

	private Map<String, String> properties;

	private List<EmployeeDTO> employees;

	public EmployeeDTO(Long id, Map<String, String> properties, List<EmployeeDTO> employees) {
		this.id = id;
		this.properties = properties;
		this.employees = employees;
	}
	
	public EmployeeDTO() {
		
	}

	public Map<String, String> getProperties() {
		return this.properties;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}
	
}