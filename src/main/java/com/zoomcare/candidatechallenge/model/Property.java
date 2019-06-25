package com.zoomcare.candidatechallenge.model;

/*
 * Property class for the table.
 */
public class Property {
	Long id;
	String key;
	String value;
	
	public Property(Long id, String key, String value) {
		this.id = id;
		this.key = key;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
