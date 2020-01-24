package com.zoomcare.candidatechallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Property {
    private final Long employee_id;
    private final String key;
    private final String value;

    public Property(@JsonProperty("employee_id") Long employee_id,
                    @JsonProperty("key") String key,
                    @JsonProperty("value") String value) {
        this.employee_id = employee_id;
        this.key = key;
        this.value = value;
    }

    public Long getEmployeeId() {
        return employee_id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
