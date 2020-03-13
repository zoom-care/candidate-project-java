package com.zoomcare.candidatechallenge.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "SUPERVISOR_ID")
    private Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "employee")
    private Set<Property> properties;
}
