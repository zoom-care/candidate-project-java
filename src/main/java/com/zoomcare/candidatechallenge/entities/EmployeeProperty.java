package com.zoomcare.candidatechallenge.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PROPERTY")
public class EmployeeProperty implements Serializable
{
    private Long employeeId;
    private String key;
    private String value;

    public EmployeeProperty()
    {}

    /**
     * Dev note: The ID field should be it's own field, rather than relying upon the employee's ID.
     */
    @Id
    @Column(name = "EMPLOYEE_ID", unique = false, nullable = false, precision = 19)
    public Long getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId)
    {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "key", unique = false, nullable = true, length = 256)
    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    @Column(name = "value", unique = false, nullable = true, length = 256)
    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
