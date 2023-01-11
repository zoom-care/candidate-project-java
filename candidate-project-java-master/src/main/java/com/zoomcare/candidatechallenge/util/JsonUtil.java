package com.zoomcare.candidatechallenge.util;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.zoomcare.candidatechallenge.models.EmployeeFull;
import com.zoomcare.candidatechallenge.models.hibernate.Employee;
import com.zoomcare.candidatechallenge.models.hibernate.Property;

public class JsonUtil {
    

    @SuppressWarnings("unchecked")
    public static String jsonifyAndStringify(EmployeeFull fuller) {
        JSONObject e = new JSONObject();
        e.put("employeeId", fuller.getEmployeeId());
        e.put("supervisorId", fuller.getSupervisorId());
        e.put("properties", jsonifyEmployeeProperties(fuller.getEmployeeProperties()));
        e.put("reportingEmployees", jsonifyReportingEmployees(fuller.getReportingEmployees()));
        return e.toJSONString();
    }

    @SuppressWarnings("unchecked")
    private static JSONArray jsonifyEmployeeProperties(List<Property> empProps) {
        JSONArray ret = new JSONArray();

        if (empProps == null || empProps.size() < 1) {
            return ret;
        }

        for (Property p: empProps) {
            JSONObject o = new JSONObject();
            o.put(p.getKey(), p.getValue());
            ret.add(o);
        }

        return ret;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject jsonifyReportingEmployees(List<Employee> reporting) {

        if (reporting == null || reporting.size() < 1) {
            return new JSONObject();
        }

        JSONObject o = new JSONObject();
        JSONArray emps = new JSONArray();

        for (Employee em: reporting) {
            emps.add(em.getId());
        }

        o.put("reportingEmployees", emps);
        return o;
    }

    @SuppressWarnings("unchecked")
    public static String jsonifyAndStringifyEmployeeList(List<Long> allIds) {
        JSONArray arr = new JSONArray();

        for (Long lucy: allIds) {
            arr.add(lucy);
        }

        JSONObject ret = new JSONObject();
        ret.put("employees", arr);

        return ret.toJSONString();
    }
}
