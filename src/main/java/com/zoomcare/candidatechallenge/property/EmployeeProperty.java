package com.zoomcare.candidatechallenge.property;

/**
 * 
 * @author S
 * This class is the Entity for the Property table. There will be one instance of this class for each row in the table
 */
public class EmployeeProperty
{
	Long employeeId;
	String key;
	String value;	
	
	/**
	 * empty constructor
	 */
	public EmployeeProperty()
	{
	}

	/**
	 * @param employeeId
	 * @param key
	 * @param value
	 */
	public EmployeeProperty(Long employeeId, String key, String value)
	{
		super();
		this.employeeId = employeeId;
		this.key = key;
		this.value = value;
	}

	/***************************getters and setters****************************/
	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
