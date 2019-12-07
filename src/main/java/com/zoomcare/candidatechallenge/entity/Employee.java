package com.zoomcare.candidatechallenge.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "SUPERVISOR_ID", nullable = false)
	private Long supervisorId;

	@OneToMany(mappedBy = "propertyId.employeeId")
	private Set<Property> properties;

	@OneToMany(mappedBy = "supervisorId")
	private Set<Employee> reportingEmployees;

	public Employee() {
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

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	public Set<Employee> getReportingEmployees() {
		return reportingEmployees;
	}

	public void setReportingEmployees(Set<Employee> reportingEmployees) {
		this.reportingEmployees = reportingEmployees;
	}
}