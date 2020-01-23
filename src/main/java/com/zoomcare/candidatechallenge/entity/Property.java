package com.zoomcare.candidatechallenge.entity;

import javax.persistence.*;

@Entity
@IdClass(PropertyId.class)
public class Property {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;

    @Id
    @Column(nullable = false)
    private String key;

    @Column
    private String value;

    public Property() {
    }

    public Property(Integer employeeId, String key, String value) {
        this.employeeId = employeeId;
        this.key = key;
        this.value = value;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

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
