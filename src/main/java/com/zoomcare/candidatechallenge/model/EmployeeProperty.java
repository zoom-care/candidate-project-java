package com.zoomcare.candidatechallenge.model;

public class EmployeeProperty {
	// e.id, e.supervisorId, p.key, p.value
	private int id;
	private Integer supervisorId;
	private String key;
	private String value;
	
	public EmployeeProperty(int id, Integer supervisorId, String key, String value) {
		
		this.id = id;
		this.supervisorId = supervisorId;
		this.key = key;
		this.value = value;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
	
}
