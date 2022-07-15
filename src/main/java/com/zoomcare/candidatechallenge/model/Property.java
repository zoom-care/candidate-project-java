package com.zoomcare.candidatechallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity representing Property table
 *
 * @author Sergio de la Torre
 * @version 1.0
 * @since 2022-07-14
 */
@Entity
@Table( name="PROPERTY" )
@Data
public class Property {
	
	@Id
	@Column( name ="KEY" )
	private String key;
	
	@Column ( name = "VALUE" )
	private String value;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "EMPLOYEE_ID")
	private Employee employee;

}
