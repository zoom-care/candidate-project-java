package com.zoomcare.candidatechallenge.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="property")
public class Property {

    private Integer employee_Id;
    @Id
    private String key;
    private String value;

    public Integer getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(Integer employee_Id) {
        this.employee_Id = employee_Id;
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
