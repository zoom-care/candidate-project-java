package com.zoomcare.candidatechallenge.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "supervisor_id")
    private Long supervisorId;

    @OneToMany
    @JoinColumn(name = "supervisor_id")
    private List<Employee> reports;

   @OneToMany(mappedBy = "employeeId")
   private List<Property> properties;

}
