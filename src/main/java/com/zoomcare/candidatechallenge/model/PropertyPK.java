package com.zoomcare.candidatechallenge.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;

@Embeddable
@Builder
public class PropertyPK implements Serializable {

    private String key;

    @Column(name = "employee_id")
    private Long employeeId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
