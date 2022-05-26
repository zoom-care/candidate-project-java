package com.zoomcare.candidatechallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	private long id;
	
	@Column(name = "SUPERVISOR_ID")
	private long supervisorId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public Employee(long id, long supervisorId) {
		super();
		this.id = id;
		this.supervisorId = supervisorId;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
