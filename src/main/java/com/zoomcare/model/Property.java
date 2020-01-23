package com.zoomcare.candidatechallenge;

/**
 * Property model representing a record in the property table
 */
public class Property {
    private long employeeId;
    private String key;
    private String value;

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

    public long setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public String setValue(String value) {
        this.value = value;
    }
}