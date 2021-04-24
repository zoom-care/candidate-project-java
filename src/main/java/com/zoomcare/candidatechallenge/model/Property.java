package com.zoomcare.candidatechallenge.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Property {
	@Id
	private int employeeId;
	private String key;
	private String value;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID", nullable = false) //specify the foreign column name
	private Employee employee;
	

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
