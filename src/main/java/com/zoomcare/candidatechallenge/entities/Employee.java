package com.zoomcare.candidatechallenge.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable
{
    private Long id;
    private Employee supervisor;
    private List<EmployeeProperty> employeeProperties;

    public Employee()
    {}

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 19)
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "SUPERVISOR_ID", unique = false, nullable = true)
    public Employee getSupervisor()
    {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor)
    {
        this.supervisor = supervisor;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeId")
    public List<EmployeeProperty> getEmployeeProperties()
    {
        return employeeProperties;
    }

    public void setEmployeeProperties(List<EmployeeProperty> employeeProperties)
    {
        this.employeeProperties = employeeProperties;
    }
}
