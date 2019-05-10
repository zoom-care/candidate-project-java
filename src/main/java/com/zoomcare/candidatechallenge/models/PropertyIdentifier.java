package com.zoomcare.candidatechallenge.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PropertyIdentifier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4375081806989523191L;

	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;

	@Column(name = "KEY")
	private String key;

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PropertyIdentifier))
			return false;
		PropertyIdentifier that = (PropertyIdentifier) o;
		return Objects.equals(getEmployeeId(), that.getEmployeeId())
				&& Objects.equals(getKey(), that.getKey());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEmployeeId(), getKey());
	}

}
