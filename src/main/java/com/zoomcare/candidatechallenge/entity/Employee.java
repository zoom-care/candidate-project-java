package com.zoomcare.candidatechallenge.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	private Long id;

	@Column(name = "supervisor_id")
	private Long supervisorId;

	@OneToMany
	@JoinColumn(name = "employee_id")
	private List<Property> properties;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "supervisor_id")
	private List<Employee> directReports;

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

	public List<Employee> getDirectReports() {
		return directReports;
	}

	public void setDirectReports(List<Employee> directReports) {
		this.directReports = directReports;
	}

}
