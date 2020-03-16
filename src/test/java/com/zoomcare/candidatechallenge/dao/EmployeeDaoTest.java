package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.exception.EmployeeProcessingException;
import com.zoomcare.candidatechallenge.exception.PropertyProcessingException;
import com.zoomcare.candidatechallenge.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeDaoTest {
    private static final Long TEST_LONG4 = new Long(4);
    private static final List<Long> TEST_LONG_LIST = new ArrayList<Long>() {{
        add(TEST_LONG4);
    }};
    private static final Long SUPERVISOR_ID_VALUE = new Long(3);
    private static final Long EMPLOYEE_ID_VALUE = new Long(4);

    @InjectMocks
    private EmployeeDao employeeDao;

    @Mock
    private JdbcTemplate jdbcTemplateMock;

    private Map<Long, Employee> testEmployeeMap;

    @Before
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        testEmployeeMap = new HashMap<>();
        Employee testEmployee = new Employee(EMPLOYEE_ID_VALUE, SUPERVISOR_ID_VALUE);
        testEmployeeMap.put(EMPLOYEE_ID_VALUE, testEmployee);
        when(jdbcTemplateMock.query(anyString(), any(PreparedStatementSetter.class), any(ResultSetExtractor.class))).thenReturn(testEmployee);
        when(jdbcTemplateMock.query(anyString(), any(ResultSetExtractor.class))).thenReturn(TEST_LONG_LIST);
    }

    @Test
    public void testAssignDirectReportListForNull() {
        try {
            List<Long> resultList = employeeDao.assignDirectReportListForNull(testEmployeeMap);
            assertThat(resultList.size()).isEqualTo(TEST_LONG_LIST.size());
        } catch (Exception e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test
    public void testAssignDirectReportListForNull_fail() {
        when(jdbcTemplateMock.query(anyString(), any(ResultSetExtractor.class))).thenThrow(RuntimeException.class);
        try {
            employeeDao.assignDirectReportListForNull(testEmployeeMap);
            Assert.fail("did not throw expected exception");
        } catch (EmployeeProcessingException e) {
            // expected
        }
    }

    @Test
    public void testAssignDirectReportListForSupervisorId() {
        try {
            employeeDao.assignDirectReportListForSupervisorId(EMPLOYEE_ID_VALUE, testEmployeeMap);
        } catch (Exception e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test
    public void testAssignDirectReportListForSupervisorId_fail() {
        when(jdbcTemplateMock.query(anyString(), any(PreparedStatementSetter.class), any(ResultSetExtractor.class))).thenThrow(RuntimeException.class);
        try {
            employeeDao.assignDirectReportListForSupervisorId(EMPLOYEE_ID_VALUE, testEmployeeMap);
            Assert.fail("did not throw expected exception");
        } catch (EmployeeProcessingException e) {
            // expected
        }
    }







    @Test
    public void testCreateBaseEmployeeEntity() {
        try {
            employeeDao.createBaseEmployeeEntity(EMPLOYEE_ID_VALUE, testEmployeeMap);
        } catch (Exception e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test
    public void testAddPropertiesToEmployees_fail() {
        when(jdbcTemplateMock.query(anyString(), any(PreparedStatementSetter.class), any(ResultSetExtractor.class))).thenThrow(RuntimeException.class);
        try {
            employeeDao.createBaseEmployeeEntity(EMPLOYEE_ID_VALUE, testEmployeeMap);
            Assert.fail("did not throw expected exception");
        } catch (EmployeeNotFoundException e) {
            // expected
        }
    }



}
