package com.zoomcare.candidatechallenge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="employee")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @ManyToOne
    Employee supervisor ;
    @OneToMany
    @JoinColumn(name="employee_id")
    List<Property> properties ;
    @OneToMany(mappedBy = "supervisor")
    Set<Employee> employeeDirectReportee;

}
