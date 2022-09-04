package com.zoomcare.candidatechallenge.Models;

public class EmployeeDTO extends Employee {

    String key;
    String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public EmployeeDTO(Long id, Long supervisorId, String key, String value) {
        super(id, supervisorId);
        this.key = key;
        this.value = value;
    }
}
