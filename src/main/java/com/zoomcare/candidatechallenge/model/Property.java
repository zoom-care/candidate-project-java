package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "PROPERTY")
@IdClass(PropertyId.class)
public class Property implements Serializable {

    @Id
    @Column(name = "KEY", nullable = false)
    private String key;

    @Column(name = "VALUE", nullable = false)
    private String value;

    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false)
    private BigInteger employeeId;

}
