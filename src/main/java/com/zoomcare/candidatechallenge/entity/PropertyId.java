package com.zoomcare.candidatechallenge.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PropertyId implements Serializable {

	private static final long serialVersionUID = 4481429086124752042L;

	@Column(name = "EMPLOYEE_ID", nullable = false)
	private Long employeeId;

	@Column(name = "KEY", nullable = false)
	private String key;

	public PropertyId() {
	}

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

}
