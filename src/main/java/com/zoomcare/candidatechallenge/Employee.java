package com.zoomcare.candidatechallenge;

import java.util.HashMap;
import java.util.List;

public class Employee {
	private Long employeeId;
	private Long supervisorId;
	private List<Long> directReports;
	private HashMap<String, String> keyValues;
	
	public Employee(Long employeeId, Long supervisorId, List<Long>directReports, HashMap<String, String> keyValues)
	{
		this.employeeId = employeeId;
		this.supervisorId = supervisorId;
		this.directReports = directReports;
		this.keyValues = keyValues;
	}
	
	public Long getEmployeeId()
	{
		return employeeId;
	}
	
	public Long getSupervisorId()
	{
		return supervisorId;
	}
	
	public List<Long> getDirectReports()
	{
		return directReports;
	}
	
	public HashMap<String, String> getKeyValues()
	{
		return keyValues;
	}
}
