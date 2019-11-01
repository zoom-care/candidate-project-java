package com.zoomcare.candidatechallenge.dtos;

/**
 * Represents the client-side DTO class for transporting details about an employee.
 *
 * @author Allen Wickham
 */
public class UiEmployeeDetails
{
    private String key;
    private String value;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
