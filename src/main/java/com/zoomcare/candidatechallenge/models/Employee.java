package com.zoomcare.candidatechallenge.models;

import java.util.List;
import java.util.Map;

public class Employee {
    private long id;    
    private List<Map<String,String>> properties;
    private List<List<Map<String,String>>> bySupervisorList;
    
    public Employee (){
    } 

    public Employee(long id, List<Map<String, String>> properties) {
        this.id = id;
        this.properties = properties;
    }

    public long getId() {
        return id;
    }   

    public List<Map<String, String>> getProperties() {
        return properties;
    }

    public List<List<Map<String, String>>> getBySupervisorList() {
        return bySupervisorList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProperties(List<Map<String, String>> properties) {
        this.properties = properties;
    }

    public void setBySupervisorList(List<List<Map<String, String>>> bySupervisorList) {
        this.bySupervisorList = bySupervisorList;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", properties=" + properties + ", bySupervisorList=" + bySupervisorList + "]";
    }    
}
