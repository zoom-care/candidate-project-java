package com.zoomcare.candidatechallenge.model;

import java.io.Serializable;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "property")
public class Property implements Serializable{
	private String key;
	private String value;
	
	@Id
	@Column(name = "employee_id")
	private Long employeeId;
	
	public Property() {
	}
	
	public Property(Long employeeId, String key, String value) {
		this.employeeId = employeeId;
		this.key = key;
		this.value = value;
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
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return this.employeeId.equals(property.getEmployeeId()) && 
        		this.key.equals(property.getKey()) &&
        		this.value.equals(property.getValue());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Property.class.getSimpleName() + "[", "]")
                .add("employeeId=" + employeeId)
                .add("key='" + key + "'")
                .add("value='" + value + "'")
                .toString();
    }
}
