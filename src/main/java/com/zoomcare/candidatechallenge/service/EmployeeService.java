package com.zoomcare.candidatechallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoomcare.candidatechallenge.mapper.EmployeeRowMapper;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeDBO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * select * from employee e left join property p on e.id = p.employee_id order by e.supervisor_id;
 */
@Service
public class EmployeeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    public String getAllEmployees() {
        JSONObject returnObj = new JSONObject();
        String sql = "select id, supervisorid, employeeid, key, value from employee e left join property p on e.id = p.employeeid";
        List<EmployeeDBO> employeesDBOList = jdbcTemplate.query(sql, new EmployeeRowMapper());
        HashMap<Integer, Employee> employeeMap = new HashMap<>();
        for (EmployeeDBO e: employeesDBOList) {
            Employee employee;
            if (!employeeMap.containsKey(e.getEmployeeId())) {
                employee = new Employee(e.getEmployeeId(), e.getSupervisorId(), e.getKey(), e.getValue());
                employeeMap.put(e.getEmployeeId(), employee);
            } else {
                employee = employeeMap.get(e.getEmployeeId());
                employee.getTitleRegionMap().put(e.getKey(), e.getValue());
            }
        }

        Set<Employee> employeesSet = new HashSet<>();
        for (Map.Entry<Integer, Employee> entry: employeeMap.entrySet()) {
            employeesSet.add(entry.getValue());
        }
        HashMap<Integer, Set<Employee>> allDirectReportsMap = getDirectReports(employeesSet);
        JSONArray returnArr = new JSONArray();
//        returnArr = seedHierarchy(allDirectReportsMap, employeesList, returnArr);
        returnObj.put("content", returnArr);
        return returnObj.toString();
    }

    private HashMap<Integer, Set<Employee>> getDirectReports(Set<Employee> employeesList) {
        HashMap<Integer, Set<Employee>> reportsMap = new HashMap<>();
        for (Employee e: employeesList) {
            Set<Employee> reportsList = new HashSet<>();
            for (Employee e2: employeesList) {
                if (e.getEmployeeId() == e2.getSupervisorId()) {
                    reportsList.add(e2);
                }
                reportsMap.put(e.getEmployeeId(), reportsList);
            }
        }
        return reportsMap;
    }

    private JSONArray seedHierarchy(HashMap<Integer, Set<EmployeeDBO>> allDirectReportsMap, List<EmployeeDBO> employeesList, JSONArray returnArr) {
        try {
            for (EmployeeDBO e: employeesList) {
                Set<EmployeeDBO> directReports = allDirectReportsMap.get(e.getEmployeeId());
                //e.getReports().addAll(directReports);
                JSONObject arrObj = new JSONObject(mapper.writeValueAsString(e));
                returnArr.put(arrObj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnArr;
    }

    public String findEmployeeById(Integer id) {
        JSONObject returnObj = new JSONObject();
        return returnObj.toString();
    }
}