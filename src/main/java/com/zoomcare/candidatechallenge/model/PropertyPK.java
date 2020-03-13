package com.zoomcare.candidatechallenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class PropertyPK implements Serializable
{
    @Column(insertable=false, updatable=false)
    private long employeeId;

    private String key;
}
