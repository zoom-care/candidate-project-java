package com.zoomcare.candidatechallenge.model;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;



@Entity
@Data
@Table(name = "PROPERTY")
@IdClass(PropertyID.class)
public class Property implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long propertyID;
//    @ManyToOne
//    @JoinColumn(name="EMPLOYEE_ID", nullable=false)
//    private Employee Employee;
//    private BigInteger EMPLOYEE_ID;
    @Id
    @Column(name = "KEY", nullable = false)
    private String key;

    @Column(name = "VALUE", nullable = false)
    private String value;

    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false)
    private BigInteger employeeID;

//    @ManyToOne
//    private Employee employee;

}
