package com.zoomcare.candidatechallenge.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class PropertyIdEntity implements Serializable {
    @Column(name = "EMPLOYEE_ID")
    private Long id;
    @Column(name = "KEY")
    private String key;
}
