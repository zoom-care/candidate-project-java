package com.zoomcare.candidatechallenge.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPERVISOR_ID")
    private EmployeeEntity supervisor;

    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeeEntity> reports = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PropertyEntity> properties = new ArrayList<>();
}
