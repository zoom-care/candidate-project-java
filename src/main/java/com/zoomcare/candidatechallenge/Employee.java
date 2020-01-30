package com.zoomcare.candidatechallenge;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

public class Employee {
    private BigInteger id;
    private BigInteger supervisorId;
    private List<Employee> subEmployees;
    List<Property> properties;
    
    protected Employee(){}

    public Employee(BigInteger id, BigInteger supervisorId){
	this.id = id;
	this.supervisorId = supervisorId;
	this.subEmployees = new ArrayList();
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

    public void setSubEmployees(List<Employee> subEmployees){
	this.subEmployees = subEmployees;
    }

    public List<Employee> getSubEmployees(){
	return subEmployees;
    }
}
