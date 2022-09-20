package com.zoomcare.candidatechallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name="EMPLOYEE")
@Getter
@Setter
@EqualsAndHashCode
public class Employee {

    @Id
    private long id;

    @Column(name = "SUPERVISOR_ID",updatable = false,insertable = false)
    private Integer supervisor;

    @ElementCollection
    @CollectionTable(name = "PROPERTY",
    joinColumns = {@JoinColumn(name = "EMPLOYEE_ID",referencedColumnName = "id")})
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
    @JsonIgnore
    private Map<String,String> properties = new HashMap<>();

    public List<AbstractMap.SimpleEntry<String,String>> getListOfProperties(){
        return this.getProperties().entrySet().stream()
                .map( entry -> new AbstractMap.SimpleEntry<>(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

}
