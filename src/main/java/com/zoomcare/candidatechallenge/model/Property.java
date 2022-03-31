package com.zoomcare.candidatechallenge.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Property {

    @EmbeddedId
    PropertyPK id;

    private String value;

    public PropertyPK getId() {
        return id;
    }

    public void setId(PropertyPK id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
