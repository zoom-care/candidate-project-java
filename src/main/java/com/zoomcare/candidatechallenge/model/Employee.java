package com.zoomcare.candidatechallenge.model;

import java.math.BigInteger;
import java.util.*;

public class Employee {
    private BigInteger id;
    private BigInteger supervisorId;

    private Map<String, String> properties;
    private Set<Employee> reports;

    public Employee() {
        reports = new HashSet<>();
        properties = new HashMap<>();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(BigInteger supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Set<Employee> getReports() {
        return reports;
    }

    public void setReports(Set<Employee> reports) {
        this.reports = reports;
    }

    public void addReport(Employee e) {
      reports.add(e);
    }

    public void addProperty(Property p) {
        properties.put(p.getKey(), p.getValue());
    }
}
