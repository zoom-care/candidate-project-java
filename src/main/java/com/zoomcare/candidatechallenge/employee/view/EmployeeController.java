package com.zoomcare.candidatechallenge.employee.view;

import com.zoomcare.candidatechallenge.employee.business.Employee;
import com.zoomcare.candidatechallenge.employee.business.EmployeeService;
import com.zoomcare.candidatechallenge.employee.business.Property;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/Employee")
    public List<EmployeeViewModel> employees() {
        return employeeService.getEmployees()
                .stream()
                .map(this::ConvertEmployeeToEmployeeViewModel)
                .collect(Collectors.toList());
    }
    @GetMapping("/Employee/{id}")
    public EmployeeViewModel employee(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployee(employeeId)
                .map(this::ConvertEmployeeToEmployeeViewModel)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    private EmployeeViewModel ConvertEmployeeToEmployeeViewModel(Employee employee){
        return EmployeeViewModel.builder()
                .id(employee.getId())
                .supervisorId(employee.getSupervisorId())
                .directReports(employee.getDirectReports()
                        .stream()
                        .map(this::ConvertEmployeeToEmployeeViewModel)
                        .collect(Collectors.toList()))
                .properties(employee.getProperties()
                        .stream()
                        .map(this::ConvertPropertyToPropertyViewModel)
                        .collect(Collectors.toList()))
                .build();
    }
    private PropertyViewModel ConvertPropertyToPropertyViewModel(Property property){
        return PropertyViewModel.builder()
                .employeeId(property.getEmployeeId())
                .key(property.getKey())
                .value(property.getValue())
                .build();
    }
}
