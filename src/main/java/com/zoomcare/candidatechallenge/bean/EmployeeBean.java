package com.zoomcare.candidatechallenge.bean;

import java.util.ArrayList;
import java.util.List;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.entity.Property;

public class EmployeeBean {

	private Long id;
	private List<PropertyBean> properties;
	private List<EmployeeBean> directReporties;

	public EmployeeBean() {
	}
	
	public EmployeeBean(Employee employee) {
		
		this.id = employee.getId();
		for (Property property : employee.getProperties()) {
			PropertyBean propertyBean = new PropertyBean(property);	
			this.addPropertyBean(propertyBean);
		}		
	}

	public EmployeeBean(Employee employee, List<Employee> directReports) {
		
		this.id = employee.getId();
		for (Property property : employee.getProperties()) {
			PropertyBean propertyBean = new PropertyBean(property);	
			this.addPropertyBean(propertyBean);
		}
		for (Employee employeeIt : directReports) {
			EmployeeBean directReport = new EmployeeBean();
			directReport.setId(employeeIt.getId());
			directReport.addProperties(employeeIt.getProperties());
			this.addDirectReport(directReport);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PropertyBean> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyBean> properties) {
		this.properties = properties;
	}

	public List<EmployeeBean> getDirectReporties() {
		return directReporties;
	}

	public void setDirectReporties(List<EmployeeBean> directReporties) {
		this.directReporties = directReporties;
	}

	public void addPropertyBean(PropertyBean propertyBean) {

		if(this.properties == null) {
			this.properties = new ArrayList<>();
		}
		this.properties.add(propertyBean);
	}

	public void addDirectReport(EmployeeBean employeeBean) {

		if(this.directReporties == null) {
			this.directReporties = new ArrayList<>();
		}
		this.directReporties.add(employeeBean);
		
	}

	public void addProperties(List<Property> properties) {

		for (Property property : properties) {
			if(this.properties == null) {
				this.properties = new ArrayList<>();
			}
			this.addProperty(property);
		}
		
	}

	private void addProperty(Property property) {

		if(this.properties == null) {
			this.properties = new ArrayList<>();
		}
		this.properties.add(new PropertyBean(property));
		
	}

}
