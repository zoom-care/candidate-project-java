package com.zoomcare.candidatechallenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Property {

	@Id
	private PropertyId propertyId;

	@Column(name = "VALUE")
	private String value;

	public Property() {
	}

	public String getKey() {
		return propertyId.getKey();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
