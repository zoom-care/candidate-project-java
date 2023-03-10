package com.zoomcare.candidatechallenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@IdClass(value = EmployeeId.class)
public class Property {
	
	@Id
	@Column(name = "key")
	private String key;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonIgnore
    private Employee employee;

	@Column(name = "value")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
