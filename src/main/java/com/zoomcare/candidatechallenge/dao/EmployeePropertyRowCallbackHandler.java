package com.zoomcare.candidatechallenge.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;

/**
 * EmployeePropertyRowCallbackHandler makes it easier to convert a complex join into a list of employees and their respective properties
 */
public class EmployeePropertyRowCallbackHandler implements RowCallbackHandler {

    private Map<Long,Employee> employeesList;

    public EmployeePropertyRowCallbackHandler() {
        this.employeesList = new HashMap<Long,Employee>();
    }

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        Long employeeId = rs.getLong("employee_id");
        Property property = new Property();
        Employee employee = this.employeesList.get(employeeId);
        if(employee==null) {
            employee = new Employee();
            employee.setId(employeeId);
            this.employeesList.put(employeeId, employee);
        }
        // add properties
        property.setKey(rs.getString("key"));
        property.setValue(rs.getString("value"));
        employee.addProperty(property);
    }

    public Map<Long, Employee> getEmployeeMap() {
        return this.employeesList;
    }

}