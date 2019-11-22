package com.zoomcare.candidatechallenge.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntity implements Serializable {

    public static final long serialVersionUID = 5102227965536374244L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="SUPERVISOR_ID")
    private Long supervisorId;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    private Set<PropertyEntity> employeeProperties = null;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "SUPERVISOR_ID")
    private Set<PropertyEntity> supervisorProperties;
}
