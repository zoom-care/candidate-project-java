package com.zoomcare.candidatechallenge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("property")
public class EmployeeProperty {

    @Id
    private Integer id;
    private String key;
    private String value;

    public EmployeeProperty(Integer id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public EmployeeProperty() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String
    toString() {
        return "EmployeeProperty{" +
                "employeeId=" + id +
                ", employeeKey='" + key + '\'' +
                ", employeeValue='" + value + '\'' +
                '}';
    }
}
