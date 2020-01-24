package com.zoomcare.candidatechallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Employee {
    private final Long id;
    private final Long supervisor_id;
    private List<Property> properties;
    private List<Employee> direct_reports;

    public Employee(@JsonProperty("id") Long id,
                    @JsonProperty("supervisor_id") Long supervisor_id) {
        this.id = id;
        this.supervisor_id = supervisor_id;
        this.properties =  null;
        this.direct_reports = null;
    }

    public Long getId() {
        return id;
    }

    public Long getSupervisorId() {
        return supervisor_id;
    }

    public List<Property> getProperties() { return properties; }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Employee> getDirectReports() { return direct_reports; }

    public void setDirectReports(List<Employee> direct_reports) {
        this.direct_reports = direct_reports;
    }
}
