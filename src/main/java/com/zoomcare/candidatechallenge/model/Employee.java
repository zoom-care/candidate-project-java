package com.zoomcare.candidatechallenge.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee")

public class Employee  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, updatable = false)
    private Long id;

    @Column(name = "supervisor_id")
    private Long supervisorId;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<Property> properties;

}
