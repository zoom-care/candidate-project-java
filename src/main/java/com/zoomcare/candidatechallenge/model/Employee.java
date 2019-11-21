package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="SUPERVISOR_ID")
    private Long supervisorId;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="employeeId", cascade = CascadeType.ALL)
    private List<Properties> properties;
}
