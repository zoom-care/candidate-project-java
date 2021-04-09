package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class PropertyId implements Serializable {

    private BigInteger employeeId;
    private String key;

}
