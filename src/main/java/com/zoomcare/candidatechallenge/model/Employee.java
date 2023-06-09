package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name= "employee")
public class Employee {

    @Id
    @Column
    private Long id;
    @Column(name="supervisor_id")
    private Long supervisorId;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Property> propertyList;

}