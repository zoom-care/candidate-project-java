/**
 * 
 */
package com.zoomcare.candidatechallenge.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author lalit
 *
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "SUPERVISOR_ID")
	@JsonIgnore
	private Employee supervisor;

	@OneToMany(mappedBy = "supervisor")
	private Set<Employee> subordinates = new HashSet<Employee>();

	@OneToMany(mappedBy = "employee")
	private Set<Property> properties = new HashSet<Property>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Employee))
			return false;
		Employee that = (Employee) o;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getSupervisor(), that.getSupervisor());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getSupervisor());
	}

}
