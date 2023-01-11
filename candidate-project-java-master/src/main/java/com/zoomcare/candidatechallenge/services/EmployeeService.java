package com.zoomcare.candidatechallenge.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zoomcare.candidatechallenge.dao.EmployeeRepository;
import com.zoomcare.candidatechallenge.dao.PropertiesRepository;
import com.zoomcare.candidatechallenge.models.EmployeeFull;
import com.zoomcare.candidatechallenge.models.hibernate.Employee;
import com.zoomcare.candidatechallenge.models.hibernate.Property;
import com.zoomcare.candidatechallenge.util.JsonUtil;



@Component
public class EmployeeService {

    private EmployeeRepository employees;
    private PropertiesRepository props;
    

    @Autowired
    public EmployeeService(EmployeeRepository employees, PropertiesRepository props) {
        this.employees = employees;
        this.props = props;
    }

    

    public String listAllEmployees() throws RuntimeException {
        List<Employee> all = employees.findAll();
        
        List<Long> allIds = new ArrayList<>();

        for (Employee emp: all) {
            allIds.add(emp.getId());
        }
                
        return JsonUtil.jsonifyAndStringifyEmployeeList(allIds);
    }

    public String getEmployeeById(String id) {

        long empId = 0l;
        try {
            empId = checkAndConvertIdFormat(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

        EmployeeFull fuller = new EmployeeFull();
        fuller.setEmployeeId(empId);

        Employee alf = employees.findByEmployeeId(empId).orElse(null);
        if (alf != null) {
            fuller.setSupervisorId(alf.getSupervisorId());
        }
        
        List<Employee> minions = employees.findBySupervisorId(empId).orElse(null);

        if (minions != null && minions.size() > 0) {
            fuller.setReportingEmployees(minions);
        }

        List<Property> empProps = props.findAllByEmployeeId(empId).orElse(null);

        if (empProps != null && empProps.size() > 0) {
            fuller.setEmployeeProperties(empProps);
        }

        return JsonUtil.jsonifyAndStringify(fuller);
    }

    private long checkAndConvertIdFormat(String id) throws RuntimeException {
        try {
            return Long.valueOf(id);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Employee ID Invalid");
        }
    }
}
