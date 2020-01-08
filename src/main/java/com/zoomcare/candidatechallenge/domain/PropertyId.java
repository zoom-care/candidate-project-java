package com.zoomcare.candidatechallenge.domain;

import java.io.Serializable;
import java.util.Objects;


// Class used for Composite Key
public class PropertyId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long employee_id;
	private String key;
	
	
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public PropertyId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PropertyId(Long employee_id, String key) {
		super();
		this.employee_id = employee_id;
		this.key = key;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		PropertyId propertyId = (PropertyId) o;
		return Objects.equals( employee_id, propertyId.employee_id ) &&
				Objects.equals( key, propertyId.key );
	}

	@Override
	public int hashCode() {
		return Objects.hash( employee_id, key);
	}
	
	

}
