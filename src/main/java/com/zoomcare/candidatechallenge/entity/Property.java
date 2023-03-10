package com.zoomcare.candidatechallenge.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "property")
public class Property implements Serializable {
	
	private static final long serialVersionUID = -2812137338249932294L;

	@Id
	@Column(name = "employee_id")
	private Long employeeId;

	@Id
	@Column(name = "key")
	private String key;

	@Column(name = "value")
	private String value;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
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