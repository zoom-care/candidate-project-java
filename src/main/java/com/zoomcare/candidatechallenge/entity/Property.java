package com.zoomcare.candidatechallenge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="property")
@IdClass(PropertyKey.class)
@Getter
@Setter
public class Property {
    @Id
    @Column(name ="employee_id")
    Long employeeId ;
    @Id
    @Column(name="key")
    String key ;
    @Column(name="value")
    String value ;

}
