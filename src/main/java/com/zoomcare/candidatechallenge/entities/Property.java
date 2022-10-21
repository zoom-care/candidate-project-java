package com.zoomcare.candidatechallenge.entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zoomcare.candidatechallenge.repositories.CompositeKey;

import lombok.Data;

@Data
@Entity
@IdClass(CompositeKey.class)
public class Property {
	
	@Id
	@JsonIgnore
	@Column(name = "employee_id")
	private BigInteger employeeId;
	
	@Id
	private String key;
	
	@Id
	private String value;
	

}
