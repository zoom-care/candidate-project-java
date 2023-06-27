package com.zoomcare.candidatechallenge.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "property")
public class PropertyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Column(name = "key")
	private String keyValue;
	
	@Column(name = "value")
	private String valueDescription;
	
	public PropertyEntity() {
		
	}

	public PropertyEntity(Long employeeId, String keyValue, String valueDescription) {
		this.employeeId = employeeId;
		this.keyValue = keyValue;
		this.valueDescription = valueDescription;
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the keyValue
	 */
	public String getKeyValue() {
		return keyValue;
	}

	/**
	 * @param keyValue the keyValue to set
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	/**
	 * @return the valueDescription
	 */
	public String getValueDescription() {
		return valueDescription;
	}

	/**
	 * @param valueDescription the valueDescription to set
	 */
	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}