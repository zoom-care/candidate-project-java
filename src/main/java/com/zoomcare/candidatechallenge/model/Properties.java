package com.zoomcare.candidatechallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTIES")
public class Properties {

	@Id
	private long id;
	
	@Column(name = "KEY")
	private String key;
	
	@Column(name = "VALUE")
	private String value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Properties(long id, String key, String value) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
	}

	public Properties() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
