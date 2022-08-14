package com.zoomcare.candidatechallenge.models.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PropertyPKId implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    private String key;

    public PropertyPKId() {
    }

    public PropertyPKId(Long employeeId, String key) {
        this.employeeId = employeeId;
        this.key = key;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyPKId that = (PropertyPKId) o;

        if (!Objects.equals(employeeId, that.employeeId)) return false;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }
}
