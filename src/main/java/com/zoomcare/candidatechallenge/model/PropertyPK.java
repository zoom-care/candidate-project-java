package com.zoomcare.candidatechallenge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PropertyPK implements Serializable {

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name="KEY")
    private String key;

    @Column(name="VALUE")
    private String value;

}
