package com.zoomcare.candidatechallenge.models.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="EMPLOYEE")
public class Employee {
    
    @Id
    @Column(name="ID")
    private long id;

    @Column(name="SUPERVISOR_ID")
    private long supervisorId;
}
