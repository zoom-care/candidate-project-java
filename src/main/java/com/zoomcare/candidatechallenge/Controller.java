package com.zoomcare.candidatechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private DBUtils dbUtils;

    @GetMapping("employee/top-level")
    public List<EmployeeDetails> getTopLevel() {
        List<Integer> topLevelList = dbUtils.getTopLevelEmployees();
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        for (Integer topLevelId : topLevelList) {
            EmployeeDetails employeeDetails1 = getEmployeeDetails(topLevelId);
            employeeDetails.add(employeeDetails1);
        }
        return employeeDetails;
    }

    @GetMapping("employee")
    public EmployeeDetails getEmployee(@RequestParam("id") Integer id) {
        return getEmployeeDetails(id);
    }

    private EmployeeDetails getEmployeeDetails(Integer id) {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setId(id);
        employeeDetails.setProperties(dbUtils.getEmployeesProperties(Arrays.asList(id)).get(id));
        List<Integer> l = dbUtils.getDirectReports(id);
        employeeDetails.setDirectReports(dbUtils.getEmployeesProperties(l));
        return employeeDetails;
    }
}

