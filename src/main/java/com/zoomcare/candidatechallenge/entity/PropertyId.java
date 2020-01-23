package com.zoomcare.candidatechallenge.entity;

import java.io.Serializable;
import java.util.Objects;

public class PropertyId implements Serializable {

    private Integer employeeId;
    private String key;

    public PropertyId() {
    }

    public PropertyId(Integer employeeId, String key) {
        this.employeeId = employeeId;
        this.key = key;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyId)) return false;
        PropertyId that = (PropertyId) o;
        return getEmployeeId().equals(that.getEmployeeId()) &&
                getKey().equals(that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getKey());
    }
}
