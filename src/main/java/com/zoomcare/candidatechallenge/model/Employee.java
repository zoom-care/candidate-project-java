package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class Employee {

    @Id
    public Long id;

    public Long supervisorId;

    @ElementCollection
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @CollectionTable(name = "property", joinColumns = @JoinColumn(name = "employee_id"))
    public Map<String, String> properties = new HashMap<>();
}
