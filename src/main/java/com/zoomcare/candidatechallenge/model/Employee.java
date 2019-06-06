package com.zoomcare.candidatechallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;


@Entity
public class Employee {
    @Id
    private Long            id;

    @Column(name = "SUPERVISOR_ID", nullable = false)
    private Long            supervisorId;

    @OneToMany(mappedBy = "id.employeeId")
    private Set<Property>   properties;


    @OneToMany(mappedBy = "supervisorId")
    private Set<Employee>   directReports;

    public Employee() {
        // default constructor
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

    public Set<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(Set<Employee> directReports) {
        this.directReports = directReports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) &&
                Objects.equals(supervisorId, employee.supervisorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, supervisorId);
    }
}
