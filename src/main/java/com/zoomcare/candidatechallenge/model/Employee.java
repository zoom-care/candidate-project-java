package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long ID;
    private Long SUPERVISOR_ID;
}
