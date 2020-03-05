package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.exception.UserDoesNotExistException;
import com.zoomcare.candidatechallenge.model.ClientEmployee;
import com.zoomcare.candidatechallenge.model.db.Employee;
import com.zoomcare.candidatechallenge.model.db.Properties;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
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

    public Employee getEmployeeById(BigInteger id) throws UserDoesNotExistException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new UserDoesNotExistException(String.format("The user does not exist with id: %s", id.toString())));
        List<Employee> supervisor = getSupervisor(employee);
        // I am not a big fan of this design because Employee is mutable now. This is not idea when trying to create thread safe classes.
        // I am however doing it because it's simple and didn't seem necessary for this assignment to create this relationship with JPA.
        Map<String, String> propertiesMap = propertiesService.getPropertiesByEmployeeId(employee.getId());
        //This is not ideal either. I would have to think a bit more to come up with a better solution. The reason I did this was because I was having trouble getting JPA to cooperate with the Map<String, String> since it wasn't part of the data model.
        ClientEmployee clientEmployee = new ClientEmployee(employee, propertiesMap, supervisor);
        return clientEmployee;
    }

    public List<ClientEmployee> getTopLevelEmployees() throws UserDoesNotExistException {
        List<Employee> topLevelEmployees = employeeRepository.getTopLevelEmployees();
        List<ClientEmployee> clientEmployees = new ArrayList<>(topLevelEmployees.size());
        for (Employee employee: topLevelEmployees) {
            List<Employee> supervisorList = getSupervisor(employee);
            Map<String, String> properties = propertiesService.getPropertiesByEmployeeId(employee.getId());
            ClientEmployee clientEmployee = new ClientEmployee(employee, properties, supervisorList);
            clientEmployees.add(clientEmployee);
        }
        return clientEmployees;
    }

    public List<Employee> getSupervisor(Employee employee) throws UserDoesNotExistException {
        List<Employee> supervisorList = new ArrayList<>();
        if (employee.getSupervisorId() != null) {
            Employee supervisor = employeeRepository.findById(employee.getSupervisorId()).orElseThrow(() -> new UserDoesNotExistException(String.format("The user does not exist with id: %s", employee.getSupervisorId().toString())));
            Map<String, String> properties = propertiesService.getPropertiesByEmployeeId(supervisor.getId());
            ClientEmployee clientEmployee = new ClientEmployee(supervisor, properties, null);
            supervisorList.add(clientEmployee);
        }
        return supervisorList;
    }
}
