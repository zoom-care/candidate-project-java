package com.zoomcare.candidatechallenge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


// Use IdClass (PropretyId class) for composite primary key
@Entity
@Table (name = "property")
@IdClass(PropertyId.class)
public class Property {
	
	
	// Relation n - 1 : properties - employee
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id", insertable = false,updatable = false)
	@JsonIgnore
	private Employee employee;
	
	//This is a field of composite key
	@Id
	@Column (name = "employee_id")
	private Long employee_id;
	
	//This is a field of composite key
	@Id
	@Column (name = "key")
	private String key;
	
	
	private String value;
	
	//Constructor
	public Property () {}
		
	public Property(Employee employee, Long employee_id, String key, String value) {
		super();
		this.employee = employee;
		this.employee_id = employee_id;
		this.key = key;
		this.value = value;
	}
	
	//Setter and Getter


	public String getKey() {
		return key;
	}

	public Employee getEmployee() {
		return employee;
	}



	public void setEmployee(Employee employee) {
		this.employee = employee;
	}




	public Long getEmployee_id() {
		return employee_id;
	}



	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
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
