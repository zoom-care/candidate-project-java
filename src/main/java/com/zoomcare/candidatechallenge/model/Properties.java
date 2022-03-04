package com.zoomcare.candidatechallenge.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PROPERTIES")
public class Properties implements Serializable {

    public Properties() {

    }

    public Properties(String k, String v) {
        super();
        this.key = k;
        this.value = v;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "key")
    private String key;
    @Column(name = "value")
    private String value;


}
