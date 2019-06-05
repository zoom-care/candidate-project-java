package com.zoomcare.candidatechallenge;

import java.util.ArrayList;

public class Employee {


	int id;

	int supervisorId;
	
	ArrayList<Property> properties; 
	
	ArrayList<Employee> reportees;
	
	public ArrayList<Employee> getReportees() {
		return reportees;
	}

	public void setReportees(ArrayList<Employee> reportees) {
		this.reportees = reportees;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	
}
