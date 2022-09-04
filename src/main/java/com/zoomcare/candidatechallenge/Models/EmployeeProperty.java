package com.zoomcare.candidatechallenge.Models;

public class EmployeeProperty {
    Long employeeId;
    String key;
    String value;

    public EmployeeProperty(Long employeeId, String key, String value) {
        this.employeeId = employeeId;
        this.key = key;
        this.value = value;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
