package com.zoomcare.candidatechallenge.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Model for Property table
 */
@Entity
public class Property implements Serializable {

    @EmbeddedId
    private EntityPropertyPk id;
    private String value;

    public EntityPropertyPk getId() {
        return id;
    }

    public void setId(EntityPropertyPk id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
