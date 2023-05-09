package com.zoomcare.candidatechallenge;

import com.zoomcare.candidatechallenge.controller.EmployeeController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ApplicationContextTest {
    @Autowired
    private EmployeeController controller;

    @Test
    public void testContext_whenApplicationRuns_thenContextLoads(){
        assertThat(controller).isNull();
    }
}
