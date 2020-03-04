package com.zoomcare.candidatechallenge.config;

import com.zoomcare.candidatechallenge.resources.EmployeeResource;
import com.zoomcare.candidatechallenge.resources.PropertiesResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(EmployeeResource.class);
        register(PropertiesResource.class);
    }

}
