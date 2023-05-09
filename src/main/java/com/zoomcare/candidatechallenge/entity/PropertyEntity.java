package com.zoomcare.candidatechallenge.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PROPERTY")
public class PropertyEntity {

    @EmbeddedId
    private PropertyIdEntity id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = false, updatable = false)
    private EmployeeEntity employee;

    @Column(name = "VALUE")
    private String value;
}
