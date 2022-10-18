package com.zoomcare.candidatechallenge.Employee.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROPERTY")
public class Property {
    @Id
    @Column(name="EMPLOYEE_ID")
    private Integer id;

    @Column(name="KEY")
    private String key;

    @Column(name="VALUE")
    private String value;


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

}
