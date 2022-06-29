package com.zoomcare.candidatechallenge.repository;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="property")
public class Property implements Serializable {

    @EmbeddedId private PropertyId id;

    @Column(name = "VALUE")
    private String value;

    public Property() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PropertyId getId() {
        return id;
    }

    public void setId(PropertyId id) {
        this.id = id;
    }
}
