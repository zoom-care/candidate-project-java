package com.zoomcare.candidatechallenge.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "supervisor_id")
	private Long supervisorId;
	
	@JoinColumn(name = "employee_id")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Property> properties;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", supervisorId=" + supervisorId + ", properties=" + properties + "]";
	}

}
