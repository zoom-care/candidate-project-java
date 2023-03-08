package com.zoomcare.candidatechallenge.model;

import javax.persistence.*;
import java.util.List;

/**
 * Model for Employee table
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "supervisor_id")
    private Long supervisorId;

    @OneToMany
    @JoinColumn(name = "supervisor_id")
    private List<Employee> employees;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<Property> properties;

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

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
