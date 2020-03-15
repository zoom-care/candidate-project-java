package com.zoomcare.candidatechallenge.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Object which represents an individual employee
 */
public class Employee {

    private Long id;
    private Long supervisorId;
    private List<Property> properties = new ArrayList<Property>();
    private List<Employee> directReports = new ArrayList<>();

    public Employee() {}

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
}
