package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.ClientEmployee;
import com.zoomcare.candidatechallenge.model.db.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private PropertiesService propertiesService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PropertiesService propertiesService) {
        this.employeeRepository = employeeRepository;
        this.propertiesService = propertiesService;
    }

    public Employee getEmployeeById(BigInteger id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        // I am not a big fan of this design because Employee is mutable now. This is not idea when trying to create thread safe classes.
        // I am however doing it because it's simple and didn't seem necessary for this assignment to create this relationship with JPA.
        Map<String, String> propertiesMap = propertiesService.getPropertiesByEmployeeId(employee.getId());
        //This is not ideal either. I would have to think a bit more to come up with a better solution. The reason I did this was because I was having trouble getting JPA to cooperate with the Map<String, String> since it wasn't part of the data model.
        ClientEmployee clientEmployee = new ClientEmployee(employee, propertiesMap);
        return clientEmployee;
    }

    public List<Employee> getTopLevelEmployees() {

        return null;
    }
}
