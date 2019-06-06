package com.zoomcare.candidatechallenge.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PropertyId implements Serializable {
    private static final long serialVersionUID = 2106739156450174677L;

    @Column(name="EMPLOYEE_ID", nullable = false)
    private Long  employeeId;

    @Column(nullable = false)
    private String      key;

    public PropertyId() {
        // default constructor
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
        PropertyId that = (PropertyId) o;
        return employeeId.equals(that.employeeId) &&
                key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, key);
    }
}
