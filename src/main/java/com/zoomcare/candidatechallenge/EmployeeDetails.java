package com.zoomcare.candidatechallenge;

import java.util.List;
import java.util.Map;

class EmployeeDetails {
    private Integer id;
    private List<List<String>> properties;
    private Map<Integer, List<List<String>>> directReports;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<List<String>> getProperties() {
        return properties;
    }

    public void setProperties(List<List<String>> properties) {
        this.properties = properties;
    }

    public Map<Integer, List<List<String>>> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(Map<Integer, List<List<String>>> directReports) {
        this.directReports = directReports;
    }

}
