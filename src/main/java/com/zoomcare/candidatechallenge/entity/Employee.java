package com.zoomcare.candidatechallenge.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SUPERVISOR_ID")
    private Integer supervisorId;

    @OneToMany
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    private List<Property> properties;

    @OneToMany(mappedBy = "supervisorId")
    private List<Employee> directReports;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId().equals(employee.getId()) &&
                Objects.equals(getSupervisorId(), employee.getSupervisorId()) &&
                Objects.equals(getProperties(), employee.getProperties()) &&
                Objects.equals(getDirectReports(), employee.getDirectReports());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSupervisorId(), getProperties(), getDirectReports());
    }
}
