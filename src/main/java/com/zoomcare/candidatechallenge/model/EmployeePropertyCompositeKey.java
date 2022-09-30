package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class EmployeePropertyCompositeKey implements Serializable {
    @Column(name = "key")
    private String key;
    @Column(name = "employee_id")
    private Long employeeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeePropertyCompositeKey)) return false;
        EmployeePropertyCompositeKey that = (EmployeePropertyCompositeKey) o;
        return key == that.key && Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, employeeId);
    }
}
