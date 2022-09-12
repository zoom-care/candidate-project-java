package com.zoomcare.candidatechallenge.entities;

import java.util.List;

public class EmployeeResource {
	private int employeeId;
	private List<List<String>> employeeProperties;
	private List<EmployeeResource> directReports;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public List<List<String>> getEmployeeProperties() {
		return employeeProperties;
	}
	public void setEmployeeProperties(List<List<String>> employeeProperties) {
		this.employeeProperties = employeeProperties;
	}
	public List<EmployeeResource> getDirectReports() {
		return directReports;
	}
	public void setDirectReports(List<EmployeeResource> directReports) {
		this.directReports = directReports;
	}
}
