package com.zoomcare.candidatechallenge.repo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

@Entity
@NamedEntityGraph(name = "Employee.directReports", attributeNodes = @NamedAttributeNode("directReports"))
public class Employee {

	@Id
	private Long id;

	private Long supervisorId;

	@OneToMany(mappedBy = "supervisorId")
	private Set<Employee> directReports;

	@OneToMany(mappedBy = "employee")
	private Set<EmployeeProperty> employeeProperties;

	public long getId() {
		return id;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public Set<EmployeeProperty> getEmployeeProperties() {
		return employeeProperties;
	}

	public void setEmployeeProperties(Set<EmployeeProperty> employeeProperties) {
		this.employeeProperties = employeeProperties;
	}

	public Set<Employee> getDirectReports() {
		return directReports;
	}

	public void setDirectReports(Set<Employee> directReports) {
		this.directReports = directReports;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
