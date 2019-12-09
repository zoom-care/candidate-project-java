package com.zoomcare.candidatechallenge.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table
public class Property {

    @EmbeddedId
    private CompositeKey propertyKey;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;
    @Column
    @Getter
    private String value;

    public String getKey() {
        return propertyKey.getKey();
    }

    @Embeddable
    public static class CompositeKey implements Serializable {

        @Column(name = "employee_id")
        private Long employee_Id;

        @Column(name = "key")
        @Getter
        private String key;
    }

}