package com.zoomcare.candidatechallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zoomcare.candidatechallenge.model.db.Employee;

import java.util.List;
import java.util.Map;

public class ClientEmployee extends Employee {

    private Map<String, String> properties;
    private List<Employee> supervisors;

    public ClientEmployee() {
    }

    public ClientEmployee(Employee employee, Map<String, String> propertiesMap, List<Employee> supervisor) {
        super(employee.getId(), employee.getSupervisorId());
        this.properties = propertiesMap;
        this.supervisors = supervisor;
    }

    public void setSupervisors(List<Employee> supervisors) {
        this.supervisors = supervisors;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> propertiesMap) {
        this.properties = propertiesMap;
    }

    public List<Employee> getSupervisors() {
        return supervisors;
    }
}
