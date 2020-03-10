package com.zoomcare.candidatechallenge;

public class Property {

    // Properties (of the property)
    private int employeeId;
    private String key;
    private String value;

    // Default constructor
    public Property() { }

    // Parameterized constructor
    /**
     * Create a property with the specified employee, key, and value
     * 
     * @param employeeId Employee ID
     * @param key
     * @param value
     */
    public Property(int employeeId, String key, String value) {
        this.employeeId = employeeId;
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getKey() + ": " + this.getValue();
    }

    // Accessors

    /**
     * @return Employee ID
     */
    public int getEmployeeId() {
        return this.employeeId;
    }

    /**
     * @return Key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return Value
     */
    public String getValue() {
        return this.value;
    }

    // Mutators

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }
}