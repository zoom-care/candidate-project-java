package com.zoomcare.candidatechallenge.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name="Property")
@Table(name="PROPERTY")
public class PropertyEntity implements Serializable {

    public static final long serialVersionUID = 4102227965536374244L;

    @Id
    @Column(name="EMPLOYEE_ID")
    private Long employeeId;
    @Column(name="KEY")
    private String key;
    @Column(name="VALUE")
    private String value;
}
