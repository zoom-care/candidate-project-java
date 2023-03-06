package com.zoomcare.candidatechallenge.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "supervisor_id")
	private Long supervisorId;

	@OneToMany
	@JoinColumn(name = "employee_id")
	private List<EmployeeProperty> properties;

	@OneToMany
	@JoinColumn(name = "supervisor_id")
	private List<Employee> employees;

	public Employee(Long id, Long supervisorId) {
		this.id = id;
		this.supervisorId = supervisorId;
	}

	public Employee() {
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public Long getEmployee_id() {
		return id;
	}

	public void setEmployee_id(Long employee_id) {
		this.id = employee_id;
	}

	public List<EmployeeProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<EmployeeProperty> properties) {
		this.properties = properties;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
