package com.zoomcare.candidatechallenge.bean;

import com.zoomcare.candidatechallenge.entity.Property;

public class PropertyBean {

	private String key;
	private String value;

	public PropertyBean(Property property) {
		this.key = property.getKey();
		this.value = property.getValue();
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
