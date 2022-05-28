package com.zoomcare.candidatechallenge.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PROPERTY")
public class Property {

	@Data
	@Embeddable
	public class compositeKeyEmployeeProperty implements Serializable{
		@Column(name = "EMPLOYEE_ID")
		private Long employeeId;
		
		@Column(name= "KEY")
		private String key;
	}
	
	@EmbeddedId
	private compositeKeyEmployeeProperty id;
	
	@Column(name="VALUE")
	private String value;

	public compositeKeyEmployeeProperty getId() {
		return id;
	}

	public void setId(compositeKeyEmployeeProperty id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Property(compositeKeyEmployeeProperty id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
