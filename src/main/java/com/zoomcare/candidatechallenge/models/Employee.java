package com.zoomcare.candidatechallenge.models;

import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain representation of an employee as it is stored in the database.
 */
public class Employee {
    private Long id;
    private Long supervisorId;
    @Transient private List<Property> properties;
    @Transient private List<Employee> directReports;

    public Employee(Long id, Long supervisorId) {
        this.id = id;
        this.supervisorId = supervisorId;
    }

    public Employee() {
        this.properties = new ArrayList<>();
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

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addEmployeeProperty(Property property) {
        this.properties.add(property);
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }
}