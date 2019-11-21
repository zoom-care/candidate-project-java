package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name="Employee")
@Table(name="EMPLOYEE")
public class Employee implements Serializable {

    public static final long serialVersionUID = 5102227965536374244L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="SUPERVISOR_ID")
    private Long supervisorId;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID")
    private List<Properties> employeeProperties;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "SUPERVISOR_ID")
    private List<Properties> supervisorProperties;


}
