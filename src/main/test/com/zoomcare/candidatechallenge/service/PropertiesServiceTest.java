package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.db.Properties;
import com.zoomcare.candidatechallenge.repository.PropertiesRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesServiceTest {

    @Mock
    private PropertiesRepository propertiesRepository;

    @InjectMocks
    private PropertiesService propertiesService;

    @Before
    public void setup() {
        List<Properties> propertiesList = new ArrayList<>();
        BigInteger bigInteger = new BigInteger("1");
        Properties properties = new Properties(bigInteger, "test_key", "test_value");
        propertiesList.add(properties);
        Mockito.when(propertiesRepository.getAllPropertiesByEmployeeId(ArgumentMatchers.any(BigInteger.class)))
                .thenReturn(propertiesList);
    }

    @Test
    public void testGetPropertiesByEmployeeId() {
        BigInteger bigInteger = new BigInteger("1");
        Map<String, String> result = propertiesService.getPropertiesByEmployeeId(bigInteger);
        Assert.assertEquals("test_value", result.get("test_key"));
    }
}
