package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@IdClass(PropertyId.class)
@Table(name = "property")
public class Property implements Serializable {
    @Id
    @Column(name = "employee_id")
    private Long employeeId;
    @Id
    @Column
    private String key;
    @Column
    private String value;
}
