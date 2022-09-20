package com.zoomcare.candidatechallenge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PROPERTY")
@IdClass(PropertyPK.class)
@Getter
@Setter
@EqualsAndHashCode
public class Property implements Serializable {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Id
    @Column(name="KEY")
    private String key;

    @Column(name="VALUE")
    private String value;

}
