package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @Column
    private Long id;
    @Column(name = "supervisor_id")
    private Long supervisorId;
}
