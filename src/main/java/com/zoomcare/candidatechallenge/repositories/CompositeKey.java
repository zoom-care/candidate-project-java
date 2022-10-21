package com.zoomcare.candidatechallenge.repositories;

import java.io.Serializable;
import java.math.BigInteger;

public class CompositeKey implements Serializable {
	
	private BigInteger employeeId;
	
	private String key;
	
	private String value;
	
}
