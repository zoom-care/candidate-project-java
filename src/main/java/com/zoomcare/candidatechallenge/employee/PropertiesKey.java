package com.zoomcare.candidatechallenge.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class PropertiesKey implements Serializable {
	private String key;
	private String value;
	@Column(name = "EMPLOYEE_ID")
	@JsonIgnore
	private long employeeId;
}
