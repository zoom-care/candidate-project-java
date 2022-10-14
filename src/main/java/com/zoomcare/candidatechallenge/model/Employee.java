package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class Employee {

    @Id
    private Long id;

    private Long supervisorId;

    @ElementCollection
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @CollectionTable(name="property", joinColumns = @JoinColumn(name = "employee_id"))
    Map<String, String> properties = new HashMap<>();
}
