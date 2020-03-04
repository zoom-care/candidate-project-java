package com.zoomcare.candidatechallenge.model;

import com.zoomcare.candidatechallenge.model.db.Employee;

import java.util.Map;

public class ClientEmployee extends Employee {

    private Map<String, String> propertiesMap;

    public ClientEmployee(Employee employee, Map<String, String> propertiesMap) {
        super(employee.getId(), employee.getSupervisorId());
        this.propertiesMap = propertiesMap;
    }

    public Map<String, String> getPropertiesMap() {
        return propertiesMap;
    }

    public void setPropertiesMap(Map<String, String> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }
}
