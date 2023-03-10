package com.zoomcare.candidatechallenge.entity;

import java.io.Serializable;

public class EmployeeId implements Serializable {

	private static final long serialVersionUID = 1268563792164235735L;

	private String key;
	
	private Employee employee;

	public EmployeeId() {
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
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
