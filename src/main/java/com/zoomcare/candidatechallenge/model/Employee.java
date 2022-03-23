package com.zoomcare.candidatechallenge.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Employee {
    @Id
    BigInteger id;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    List<Employee> employees = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "employee_id")
    List<Property> properties = new ArrayList<>();
}
