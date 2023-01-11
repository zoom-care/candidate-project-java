package com.zoomcare.candidatechallenge.employee;

import java.util.List;
import java.util.Map;
/**
 * @author Pabel Lopez
 */

public class EmployeeBean {
    private long id;    
    private List<Map<String,String>> properties;
    private List<List<Map<String,String>>> reportList ;
    public EmployeeBean (){
    } 

    public EmployeeBean(long id, List<Map<String, String>> properties) {
        this.id = id;
        this.properties = properties;
    }

    public long getId() {
        return id;
    }   

    public List<Map<String, String>> getProperties() {
        return properties;
    }

    public List<List<Map<String, String>>> getReportList() {
        return reportList;
    }
    
    public void setId(long id) {
        this.id = id;
    }
     
    public void setProperties(List<Map<String, String>> properties) {
        this.properties = properties;
    }

    public void setReportList(List<List<Map<String, String>>> reportList) {
        this.reportList = reportList;
    }
    
    @Override
    public String toString() {
        return "EmployeeBean [id=" + id + ", properties=" + properties + ", reportList=" + reportList + "]";
    }    
}