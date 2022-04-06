package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity(name = "property")
@Table
public class EmployeeProperty {

    @Data
    @Embeddable
    public static class EmployeePropertyId implements Serializable {
        @Column(name = "employee_id")
        private Long employee;

        @Column(name = "key")
        private String key;
    }

    @EmbeddedId
    private EmployeePropertyId id;

    @Column(name = "value")
    private String value;


}
