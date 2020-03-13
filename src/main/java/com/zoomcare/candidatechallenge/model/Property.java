package com.zoomcare.candidatechallenge.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Property implements Serializable
{
    @EmbeddedId
    private PropertyPK id;

    private String value;

    @MapsId("employeeId")
    @ManyToOne
    @JoinColumn
    private Employee employee;
}
