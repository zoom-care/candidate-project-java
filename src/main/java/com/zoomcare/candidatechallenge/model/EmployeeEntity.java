package com.zoomcare.candidatechallenge.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {
	
	@Id
	private long id;
	
	@OneToOne
	@JoinColumn(name = "SUPERVISOR_ID")
	private EmployeeEntity supervisor;
	
	@OneToMany(mappedBy = "supervisor")
	private List<EmployeeEntity> employees = new ArrayList<>();
	
	
	@OneToMany
	@JoinColumn(name="EMPLOYEE_ID")
	private List<Property> properties = new ArrayList<>();


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public EmployeeEntity getSupervisor() {
		return supervisor;
	}


	public void setSupervisor(EmployeeEntity supervisor) {
		this.supervisor = supervisor;
	}


	public List<EmployeeEntity> getEmployees() {
		return employees;
	}


	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}


	public List<Property> getProperties() {
		return properties;
	}


	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}


	public EmployeeEntity(long id, EmployeeEntity supervisor, List<EmployeeEntity> employees, List<Property> properties) {
		super();
		this.id = id;
		this.supervisor = supervisor;
		this.employees = employees;
		this.properties = properties;
	}


	public EmployeeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
