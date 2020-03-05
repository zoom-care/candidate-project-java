package com.zoomcare.candidatechallenge.config;

import com.zoomcare.candidatechallenge.exception.InvalidDataTypeExceptionMapper;
import com.zoomcare.candidatechallenge.exception.UserDoesNotExistMapper;
import com.zoomcare.candidatechallenge.resources.EmployeeResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(EmployeeResource.class);
        register(UserDoesNotExistMapper.class);
        register(InvalidDataTypeExceptionMapper.class);
    }

}
