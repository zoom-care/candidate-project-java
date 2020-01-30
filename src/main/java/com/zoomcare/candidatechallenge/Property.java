package com.zoomcare.candidatechallenge;

import java.math.BigInteger;

public class Property {
    private BigInteger employeeId;
    private String key;
    private String value;

    public Property(){}

    public Property(BigInteger employeeId, String key, String value){
	this.employeeId = employeeId;
	this.key = key;
	this.value = value;
    }

    public BigInteger getEmployeeId(){
	return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
	this.employeeId = employeeId;
    }

    public String getKey(){
	return key;
    }

    public void setKey(String key){
	this.key = key;
    }

    public String getValue(){
	return value;
    }

    public void setValue(String value){
	this.value = value;
    }
}
