package com.zoomcare.candidatechallenge.models.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PROPERTIES")
public class Property {
    
    @Id
    @Column(name="EMPLOYEE_ID")
    private long employeeId;

    @Column(name="KEY")
    private String key;

    @Column(name="VALUE")
    private String value;
}
