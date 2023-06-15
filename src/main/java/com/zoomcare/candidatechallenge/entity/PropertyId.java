package com.zoomcare.candidatechallenge.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long employeeId;

	private String key;
}