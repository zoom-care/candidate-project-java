package com.zoomcare.candidatechallenge.model;

/**
 * Property model representing a record in the property table
 */
public class Property {
    private long employeeId;
    private String key;
    private String value;

    public Property () {

    }

    public Property(long employeeId, String key, String value) {
        this.employeeId = employeeId;
        this.key = key;
        this.value = value;
    }
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
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