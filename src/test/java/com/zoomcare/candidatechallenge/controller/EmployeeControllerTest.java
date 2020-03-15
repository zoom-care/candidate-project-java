package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exception.EmployeeIdInvalidException;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.exception.EmployeeProcessingException;
import com.zoomcare.candidatechallenge.exception.PropertyProcessingException;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class EmployeeControllerTest {
    private static final String TEST_STRING = "testString";
    private static final String TEST_LONG4 = "4";

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeServiceMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSingleEmployee() {
        try {
            given(this.employeeServiceMock.processIndividualEmployee(anyLong())).willReturn(TEST_STRING);
            assertThat(employeeController.getSingleEmployee(TEST_LONG4)).isEqualTo(TEST_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetSingleEmployee_InvalidEmployeeId() {
        try {
            employeeController.getSingleEmployee(TEST_STRING);
            Assert.fail("Expected EmployeeIdInvalidException not thrown");
        } catch (EmployeeProcessingException e) {
            Assert.fail("wrong exception thrown - EmployeeProcessingException");
        } catch (EmployeeNotFoundException e) {
            Assert.fail("wrong exception thrown - EmployeeNotFoundException");
        } catch (EmployeeIdInvalidException e) {
            // expected
        } catch (PropertyProcessingException e) {
            Assert.fail("wrong exception thrown - PropertyProcessingException");
        }
    }

    @Test
    public void testGetAllEmployees() {
        try {
            given(this.employeeServiceMock.processAllEmployees()).willReturn(TEST_STRING);
            assertThat(employeeController.getAllEmployees()).isEqualTo(TEST_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
