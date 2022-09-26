package com.zoomcare.candidatechallenge.model.entity;

import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Map;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;

    @ElementCollection
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @CollectionTable
            (
                    name = "property",
                    joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")}
            )
    private Map<String, String> properties;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supervisor")
    private List<Employee> directReports;

}
