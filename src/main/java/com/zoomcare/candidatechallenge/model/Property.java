package com.zoomcare.candidatechallenge.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Property implements Serializable {
    @EmbeddedId
    private PropertyPK id;
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
