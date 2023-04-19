package com.zoomcare.candidatechallenge.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PROPERTIES")
public class Properties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;



    @Column(name = "SUPERVISOR_ID")
    private Long supervisorId;
    @Column(name = "property_key")
    private String key;
    @Column(name = "property_value")
    private String value;

    public Properties(String key1, String value1) {
    }

    public Properties() {

    }


    // Getters and setters

}

