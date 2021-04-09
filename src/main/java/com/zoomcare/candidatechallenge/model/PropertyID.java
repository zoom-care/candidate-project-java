package com.zoomcare.candidatechallenge.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class PropertyID implements Serializable {
    private BigInteger employeeID;
    private String key;
}
