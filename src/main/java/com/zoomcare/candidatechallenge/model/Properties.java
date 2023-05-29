package com.zoomcare.candidatechallenge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity for table Property
 */
@Entity
@Table(name="PROPERTY")
public class Properties implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	@Id
	private String key;
	
	@Id
	private String value;

	public Properties() {	}
	
	public Properties(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Properties(Employee employee, String key, String value) {
		this.employee = employee;
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("Properties[employeeId: %d, key: %s, value: %s]", employee.getId(), key, value);
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}