package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Data
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private BigInteger id;

    @Column(name = "SUPERVISOR_ID", nullable = false)
    private BigInteger supervisorId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="EMPLOYEE_ID")
    Set<Property> properties;

}
