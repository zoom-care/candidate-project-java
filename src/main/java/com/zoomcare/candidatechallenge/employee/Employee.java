/**
 * 
 */
package com.zoomcare.candidatechallenge.employee;

import java.util.List;

import com.zoomcare.candidatechallenge.property.EmployeeProperty;

/**
 * @author S
 * This class is the Entity for the Employee table. There will be one instance of this class for each row in the Employee table
 */
public class Employee
{

	private long id;
	private Long supervisorId;
	List<EmployeeProperty> properties;
	List<Employee> directReports;
	
	/**
	 * empty constructor
	 */
	public Employee()
	{
	}
	
	/**
	 * This constructor is for an employee who does not have a supervisor. It sets the supervisor to null.
	 * @param id
	 * @
	 */
	public Employee(long id)
	{
		super();
		this.id = id;
		this.supervisorId = null;
	}
	
	/**
	 * @param id
	 * @param supervisorId Can be null
	 */
	public Employee(long id, Long supervisorId)
	{
		super();
		this.id = id;
		this.supervisorId = supervisorId;
	}
	
	/***************************getters and setters****************************/
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the supervisorId
	 */
	public Long getSupervisorId() {
		return supervisorId;
	}
	/**
	 * @param supervisorId the supervisorId to set
	 */
	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}
	/**
	 * @return the properties
	 */
	public List<EmployeeProperty> getProperties() {
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(List<EmployeeProperty> properties) {
		this.properties = properties;
	}
	/**
	 * @return the directReports
	 */
	public List<Employee> getDirectReports() {
		return directReports;
	}
	/**
	 * @param directReports the directReports to set
	 */
	public void setDirectReports(List<Employee> directReports) {
		this.directReports = directReports;
	}
}
