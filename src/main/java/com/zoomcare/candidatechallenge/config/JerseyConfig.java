package com.zoomcare.candidatechallenge.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.zoomcare.candidatechallenge.services.EmployeeServiceImpl;

@Configuration
public class JerseyConfig extends ResourceConfig{

	public JerseyConfig() {
		register(EmployeeServiceImpl.class);
	}
}
