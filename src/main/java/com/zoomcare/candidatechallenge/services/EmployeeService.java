package com.zoomcare.candidatechallenge.services;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.entities.Property;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getEmployees() {
        return employeeRepo.getEmployees();
    }

    public EmployeeDto getEmployeeById(int id) throws Exception {
        Optional<Employee> employeeOpt = employeeRepo.getEmployeeById(id);
        if (!employeeOpt.isPresent()) throw new Exception("No existe un empleado con ese identificador"); // TODO: Custom exception
        List<Employee> employeeList = employeeRepo.getEmployees();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setProperties(employeeRepo.getEmployeeProperties(id));
        employeeDto.setSubEmployees(getSubEmployees(id, employeeList));
        return employeeDto;
    }

    public List<EmployeeDto> getSubEmployees(int employeeId, List<Employee> employeeList) {
        List<Employee> subEmployees = employeeList.stream().filter(employee -> employee.getSupervisorId() == employeeId).collect(Collectors.toList());
        if (subEmployees.isEmpty()) return null;
        List<EmployeeDto> subEmployeesDto = new ArrayList<>();
        for (Employee employee : subEmployees) {
            EmployeeDto sub = new EmployeeDto();
            sub.setProperties(employeeRepo.getEmployeeProperties(employee.getEmployeeId()));
            sub.setSubEmployees(getSubEmployees(employee.getEmployeeId(), employeeList));
            subEmployeesDto.add(sub);
        }
        return subEmployeesDto;
    }

}
