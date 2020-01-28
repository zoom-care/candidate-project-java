package com.zoomcare.candidatechallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Property model representing a record in the property table
 */
@Entity
@Table(name="property")
public class Property {

    @Id
    @Column(name="employee_id")
    @JsonIgnore
    private long employeeId;
    private String key;
    private String value;

    public Property () {

    }

    public Property(long employeeId, String key, String value) {
        this.employeeId = employeeId;
        this.key = key;
        this.value = value;
    }
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
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