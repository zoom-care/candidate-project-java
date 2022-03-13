package com.zoomcare.candidatechallenge.objects;

public class Properties {
    
    private int employeeId;
    private String key;
    private String value;

    public Properties(int employeeId, String key, String value) {
        this.employeeId = employeeId;
        this.key = key;
        this.value = value;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public Properties setEmployeeID(int employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public Properties setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Properties setValue(String value) {
        this.value = value;
        return this;
    }
}
