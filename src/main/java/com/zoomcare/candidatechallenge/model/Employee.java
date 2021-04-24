package com.zoomcare.candidatechallenge.model;

import java.util.List;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	Long id;
	Long supervisorId;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private List<Property> properties;

	public Employee() {
	}
	
	public Employee(Long id, Long supervisorId) {
		this.id = id;
		this.supervisorId = supervisorId;
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return this.id.equals(employee.getId()) && this.supervisorId.equals(employee.getSupervisorId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("id=" + id)
//                .add("supervisorId='" + supervisorId + "'")
                .toString();
    }

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
}
