package com.zoomcare.candidatechallenge;

import java.math.BigInteger;
import java.util.List;

public class Employee {
    private BigInteger id;
    private BigInteger supervisorId;
    List<Property> properties;
    
    protected Employee(){}

    public Employee(BigInteger id, BigInteger supervisorId){
	this.id = id;
	this.supervisorId = supervisorId;
    }

    public BigInteger getId(){
	return id;
    }

    public void setId(BigInteger id){
	this.id = id;
    }

    public BigInteger getSupervisorId(){
	return supervisorId;
    }

    public void setSupervisorId(BigInteger supervisorId){
	this.supervisorId = supervisorId;
    }

    public List<Property> getProperties(){
	return this.properties;
    }

    public void setProperties(List<Property> properties){
	this.properties = properties;
    }
}
