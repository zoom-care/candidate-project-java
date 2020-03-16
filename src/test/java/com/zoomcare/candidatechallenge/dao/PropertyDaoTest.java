package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.exception.PropertyProcessingException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class PropertyDaoTest {
    private static final String TEST_STRING = "testString";
    private static final Long SUPERVISOR_ID_VALUE = new Long(3);
    private static final Long EMPLOYEE_ID_VALUE = new Long(4);

    @InjectMocks
    private PropertyDao propertyDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplateMock;

    private Map<Long, Employee> testEmployeeMap;

    @Before
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Property testProperty = new Property(TEST_STRING, TEST_STRING);
        testEmployeeMap = new HashMap<>();
        Employee testEmployee = new Employee(EMPLOYEE_ID_VALUE, SUPERVISOR_ID_VALUE);
        testEmployeeMap.put(EMPLOYEE_ID_VALUE, testEmployee);
        when(namedParameterJdbcTemplateMock.query(anyString(), anyMap(), any(ResultSetExtractor.class))).thenReturn(null);
    }

    @Test
    public void testAddPropertiesToEmployees() {
        try {
            propertyDao.addPropertiesToEmployee(testEmployeeMap);
            System.out.println("");
        } catch (PropertyProcessingException e) {
            e.printStackTrace();
            Assert.fail("unexpected exception");
        }
    }

    @Test
    public void testAddPropertiesToEmployees_fail() {
        when(namedParameterJdbcTemplateMock.query(anyString(), anyMap(), any(ResultSetExtractor.class))).thenThrow(RuntimeException.class);
        try {
            propertyDao.addPropertiesToEmployee(testEmployeeMap);
            Assert.fail("did not throw expected exception");
        } catch (PropertyProcessingException e) {
            // expected
        }
    }
}
