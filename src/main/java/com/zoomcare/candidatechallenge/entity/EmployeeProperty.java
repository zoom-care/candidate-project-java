package com.zoomcare.candidatechallenge.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "property")
public class EmployeeProperty implements Serializable {

	@Id
	@GeneratedValue
	private Long employee_id;

	private String key;

	private String value;

	public Long getEmployeeId() {
		return employee_id;
	}

	public void setEmployeeId(Long employeeId) {
		this.employee_id = employeeId;
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
