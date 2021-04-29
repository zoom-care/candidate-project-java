package com.zoomcare.candidatechallenge.employee;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "PROPERTY")
public class Properties {
	@EmbeddedId
	private PropertiesKey propertiesKey;

}
