package com.zoomcare.candidatechallenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoomcare.candidatechallenge.dao.EmployeeDao;
import com.zoomcare.candidatechallenge.dao.PropertyDao;
import com.zoomcare.candidatechallenge.exception.EmployeeProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    private static final String TEST_STRING = "TestString";
    private static final Long TEST_LONG4 = new Long(4);
    private static final List<Long> TEST_LONG_LIST = new ArrayList<Long>() {{
                                                        add(TEST_LONG4);
                                                        }};


    @InjectMocks
    private  EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDaoMock;

    @Mock
    private PropertyDao propertyDaoMock;

    @Mock
    private ObjectMapper objectMapperMock;

    @Mock
    private JsonProcessingException jsonProcessingExceptionMock;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        doNothing().when(employeeDaoMock).assignDirectReportListForSupervisorId(anyLong(), any());
        doNothing().when(employeeDaoMock).createBaseEmployeeEntity(anyLong(), any());
        when(employeeDaoMock.assignDirectReportListForNull(any())).thenReturn(TEST_LONG_LIST);
        doNothing().when(propertyDaoMock).addPropertiesToEmployee(any());
        when(objectMapperMock.writeValueAsString(any())).thenReturn(TEST_STRING);
    }

    @Test
    public void testProcessIndividualEmployee()  {
        try {
            String resultString = employeeService.processIndividualEmployee(TEST_LONG4);
            assertThat(resultString).isEqualTo(TEST_STRING);
        } catch (Exception e) {
            Assert.fail("unexpected exception " + e.getMessage());
        }
    }

    @Test
    public void testProcessIndividualEmployee_objectMapperFails() throws JsonProcessingException {
        try {
            when(objectMapperMock.writeValueAsString(any())).thenThrow(jsonProcessingExceptionMock);
            employeeService.processIndividualEmployee(TEST_LONG4);
            Assert.fail("expected exception not thrown");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(EmployeeProcessingException.class);
        }

    }

    @Test
    public void testProcessAllEmployees() {
        try {
            String resultString = employeeService.processAllEmployees();
            assertThat(resultString).isEqualTo(TEST_STRING);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("unexpected exception " + e.getMessage());
        }
    }

    @Test
    public void testProcessAllEmployees_objectMapperFails() {
        try {
            when(objectMapperMock.writeValueAsString(any())).thenThrow(jsonProcessingExceptionMock);
            employeeService.processAllEmployees();
            Assert.fail("expected exception not thrown");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(EmployeeProcessingException.class);
        }
    }
}
