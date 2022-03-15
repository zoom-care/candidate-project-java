package com.zoomcare.candidatechallenge.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    private Integer id;
    private Integer supervisorId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee_Id")
    private List<Property> properties;

    @Transient
    private List<Employee> reports;

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

    public List<Employee> getReports() {
        return reports;
    }

    public void setReports(List<Employee> reports) {
        this.reports = reports;
    }
}
