package com.zoomcare.candidatechallenge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Self reference to own table
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    @JsonBackReference
    private Employee supervisor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="supervisor", orphanRemoval = true)
    @JsonManagedReference
    private List<Employee> directEmployees = new ArrayList<>();

    // Map to table property with columns key and value
    @ElementCollection
    @CollectionTable(name = "property",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> properties;
}
