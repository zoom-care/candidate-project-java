package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "employee")
@Table
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    private List<Employee> reporters = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<EmployeeProperty> props = new ArrayList<>();

}

