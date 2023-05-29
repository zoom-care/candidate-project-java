package com.zoomcare.candidatechallenge.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity for table Employee
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private Long supervisorId;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Properties> properties;
	
	public Employee() { }

	public Employee(Long id, Long supervisorId, List<Properties> properties) {
		this.id = id;
		this.supervisorId = supervisorId;
		this.properties = properties;
	}

	@Override
	public String toString() {
		return String.format("Employee[id: %d, supervisorId: %d]", id, supervisorId);
	}

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

	public List<Properties> getProperties() {
		return properties;
	}

	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}

}