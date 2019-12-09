package com.zoomcare.candidatechallenge.controller;

import java.net.URL;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

public abstract class BaseControllerIT {

    @LocalServerPort
    int port;

    URL employeeEndpoint;

    @Autowired
    TestRestTemplate template;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        employeeEndpoint = new URL("http://localhost:" + port + "/v1/employee");
    }
}
