package com.zoomcare.candidatechallenge.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for the EmployeeService class.  This only tests some of the functionality of the employee service.
 * Additional test cases would be a good idea for more complete coverage.
 */

public class EmployeeServiceTest {

    private EmployeeService service;

    @Before
    public void setup() {
        service = new EmployeeService();
    }

    @Test
    public void testGetSupervisorId() {
        long result = service.getSupervisorId(getRow());
        Assert.assertEquals(13L, result);
    }

    @Test
    public void testGetNestedReports() {

        Map<String, Object> supervisor = new HashMap<>();
        supervisor.put("id", 14L);
        service.getNestedReports(supervisor, getEmployeesBySuper());

        Assert.assertTrue(supervisor.containsKey("direct_reports"));
        Assert.assertEquals(3, ((List)supervisor.get("direct_reports")).size());
        Map<String,Object> employee21 = (Map<String,Object>)((List)supervisor.get("direct_reports")).get(0);
        Assert.assertEquals(2, ((List)employee21.get("direct_reports")).size());
    }

    private Map<String,Object> getRow() {
        Map<String, Object> row = new HashMap<>();
        row.put("id", 12L);
        row.put("supervisor_id", 13L);
        row.put("key", "title");
        row.put("value", "Sales Representative");
        return row;
    }

    private Map<Long, List<Map<String,Object>>> getEmployeesBySuper() {

        Map<String,Object> emp1 = new HashMap<>();
        emp1.put("id", 21L);
        Map<String,Object> emp2 = new HashMap<>();
        emp2.put("id", 22L);
        Map<String,Object> emp3 = new HashMap<>();
        emp3.put("id", 23L);
        List<Map<String,Object>> empForSuper = new ArrayList<>();
        empForSuper.add(emp1);
        empForSuper.add(emp2);
        empForSuper.add(emp3);

        Map<String,Object> emp4 = new HashMap<>();
        emp4.put("id", 24L);
        Map<String,Object> emp5 = new HashMap<>();
        emp5.put("id", 25L);
        List<Map<String,Object>> empForSuper2 = new ArrayList<>();
        empForSuper2.add(emp4);
        empForSuper2.add(emp5);

        Map<Long, List<Map<String,Object>>> empsBySuper = new HashMap<>();
        empsBySuper.put(14L, empForSuper);
        empsBySuper.put(21L, empForSuper2);
        return empsBySuper;
    }

}
