package com.zoomcare.candidatechallenge.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="employee")
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supervisorId;

    @OneToMany(mappedBy="employee", cascade = CascadeType.ALL)
    private List<Properties> properties;

}
