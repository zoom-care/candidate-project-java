package com.zoomcare.candidatechallenge.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class BeansTest {

    @InjectMocks
    private Beans beans;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testObjectMapperExists() {
        assertThat(beans.objectMapper()).isInstanceOf(ObjectMapper.class);
    }

}
