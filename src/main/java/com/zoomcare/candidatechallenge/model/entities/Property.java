package com.zoomcare.candidatechallenge.model.entities;

import com.zoomcare.candidatechallenge.model.PropertyId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(PropertyId.class)
public class Property implements Serializable {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Id
    @Column(nullable = false)
    private String key;

    private String value;

}
