package com.zoomcare.candidatechallenge.models;

/**
 * Domain representation of a property of an employee as it is stored in the database.
 */
public class Property {
    private Long employeeId;
    private String key;
    private String value;

    public Property(Long employeeId, String key, String value) {
        this.employeeId = employeeId;
        this.key = key;
        this.value = value;
    }

    public Property() {}

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}