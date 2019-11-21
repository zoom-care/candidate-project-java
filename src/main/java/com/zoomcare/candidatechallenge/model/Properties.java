package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="Properties")
@Table(name="PROPERTY")
public class Properties {

    @Id
    @Column(name="EMPLOYEE_ID")
    private Long employeeId;
    @Column(name="KEY")
    private String key;
    @Column(name="VALUE")
    private String value;
}
