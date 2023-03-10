package com.zoomcare.candidatechallenge.model;

import java.util.List;

public class EmployeeDto {

	private Long id;
	private Long supervisorId;
	private List<PropertyDto> properties;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public List<PropertyDto> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyDto> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", supervisorId=" + supervisorId + ", properties=" + properties + "]";
	}

}
