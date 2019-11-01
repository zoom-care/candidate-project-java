package com.zoomcare.candidatechallenge.dtos;

import java.util.List;

/**
 * Represents the client-side DTO class for transporting high-level information about an employee.
 *
 * @author Allen Wickham
 */
public class UiEmployee
{
    private Long employeeId;
    private UiEmployee supervisor;
    private List<UiEmployeeDetails> employeeDetails;

    public Long getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId)
    {
        this.employeeId = employeeId;
    }

    public UiEmployee getSupervisor()
    {
        return supervisor;
    }

    public void setSupervisor(UiEmployee supervisor)
    {
        this.supervisor = supervisor;
    }

    public List<UiEmployeeDetails> getEmployeeDetails()
    {
        return employeeDetails;
    }

    public void setEmployeeDetails(List<UiEmployeeDetails> employeeDetails)
    {
        this.employeeDetails = employeeDetails;
    }
}
