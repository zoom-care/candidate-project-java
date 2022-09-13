package com.zoomcare.candidatechallenge.model.dto;

public class EmployeePropertyDTO {

    private String key;
    private String value;

    public EmployeePropertyDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public EmployeePropertyDTO() {}

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
