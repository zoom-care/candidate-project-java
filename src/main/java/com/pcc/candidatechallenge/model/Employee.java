package com.pcc.candidatechallenge.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employee
{
    private long Id;
    private long supervisorId;
    private Map<String, String> properties;
    private List<Employee> directReportsArray;

    public Employee()
    {
        Id = 0L;
        supervisorId = 0L;
        properties = new HashMap<>();
        directReportsArray = new ArrayList<>();
    }

    public void setId(long Id)
    {
        this.Id = Id;
    }

    public long getId()
    {
        return this.Id;
    }

    public void setSupervisorId(long Id)
    {
        this.supervisorId = Id;
    }

    public long getSupervisorId()
    {
        return this.supervisorId;
    }

    public void SetDirectReportsArray(List<Employee> reports) {  this.directReportsArray = reports; }

    public List<Employee> getDirectReportsArray()  { return this.directReportsArray; }

    public void setProperties(Map<String, String> properties)
    {
        this.properties = properties;
    }

    public Map<String, String> getProperties()
    {
        return this.properties;
    }
}