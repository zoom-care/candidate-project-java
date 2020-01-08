package com.zoomcare.candidatechallenge.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	private Long supervisor_id;
	
	// Relation 1 - n  : employee with properties
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "employee")
	private Set<Property> properties = new HashSet<Property>();
	
	
	// Relation n- 1  : employees with  supervisor (employee), it is a self reference
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="supervisor_id", insertable = false,updatable = false)
	@JsonIgnore
	private Employee supervisor;

	// Relation 1 - n : supervisor with subordinates (employee), it is a self reference
	@OneToMany(mappedBy="supervisor")
	private Set<Employee> subordinates = new HashSet<Employee>();

	
	// Constructors 
	
	public Employee (Long id, Long superior_id) {
		super();
		this.id = id;
		this.supervisor_id = superior_id;
				
	}
	
	public Employee (Long id) {
		super();
		this.id = id;
				
	}
	
	
	public Employee () {
		super();
				
	}
	
	// Setters and Getters
		
	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSupervisor_id() {
		return supervisor_id;
	}
	public void setSupervisor_id(Long supervisor_id) {
		this.supervisor_id = supervisor_id;
	}

	
	

}
