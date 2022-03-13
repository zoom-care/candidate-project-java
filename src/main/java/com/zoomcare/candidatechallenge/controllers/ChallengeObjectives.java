package com.zoomcare.candidatechallenge.controllers;

import java.util.ArrayList;
import java.util.List;

import com.zoomcare.candidatechallenge.objects.Employee;
import com.zoomcare.candidatechallenge.objects.EmployeeProperties;
import com.zoomcare.candidatechallenge.objects.Properties;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;
import com.zoomcare.candidatechallenge.repositories.PropertiesRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengeObjectives {
    
    EmployeeRepository employeeRepository = new EmployeeRepository();
    PropertiesRepository propertiesRepository = new PropertiesRepository();

    @RequestMapping("/") 
    public String test() {
        StringBuilder menu = new StringBuilder();
        menu.append("<h1>Navigation Assistant</h1>");
        menu.append("<br/><h4>/employees/list: </h4><p>returns a full list of employees</p>");
        menu.append("<br/><h4>/employees/list/top-level: </h4><p>returns a list of all top-level employees</p>");
        menu.append("<br/><h4>/employees/{id}: </h4><p>displays information on employee with \"id\" provided</p>");
        return menu.toString();
    }

    @RequestMapping("/employees/list")
    public List<EmployeeProperties> getListOfEmployees() {
        List<EmployeeProperties> employeeProperties = new ArrayList<>();
        List<Employee> employees = employeeRepository.getListOfEmployees();
        for (Employee employee : employees) {
            List<Properties> properties = propertiesRepository.getListOfPropertiesById(employee.getId());
            employeeProperties.add(new EmployeeProperties(employee, properties));
        }
        this.closeConnections();
        return employeeProperties;
    }

    @RequestMapping("/employees/list/top-level")
    public List<EmployeeProperties> getListOfTopLevelEmployees() {
        List<EmployeeProperties> employeeProperties = new ArrayList<>();
        List<Employee> employees = employeeRepository.getListOfTopLevelEmployees();
        for (Employee employee : employees) {
            List<Properties> properties = propertiesRepository.getListOfPropertiesById(employee.getId());
            employeeProperties.add(new EmployeeProperties(employee, properties));
        }
        this.closeConnections();
        return employeeProperties;
    }

    @RequestMapping("/employees/{id}")
    public EmployeeProperties getEmployeeById(@PathVariable("id") int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        List<Properties> properties = propertiesRepository.getListOfPropertiesById(id);
        this.closeConnections();
        return new EmployeeProperties(employee, properties);
    }

    private void closeConnections() {
        this.employeeRepository.closeConnection();
        this.propertiesRepository.closeConnection();
    }
}
