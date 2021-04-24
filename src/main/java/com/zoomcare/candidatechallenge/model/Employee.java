package com.zoomcare.candidatechallenge.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	
	@Id
	@Column(name = "ID")
	private int id;
	private Integer supervisorId; // nullable
	
	@OneToMany(mappedBy="employee") // field employee in PROPERTY table owns the relationship
	private List<Property> properties;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getSupervisor_id() {
		return supervisorId;
	}
	public void setSupervisor_id(int supervisorId) {
		this.supervisorId = supervisorId;
	}
	
	

}
