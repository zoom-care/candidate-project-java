package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.Supervisor;
import com.zoomcare.candidatechallenge.entity.EmployeeEntity;
import com.zoomcare.candidatechallenge.entity.PropertyEntity;
import com.zoomcare.candidatechallenge.entity.PropertyKey;
import com.zoomcare.candidatechallenge.exceptions.EmployeeInternalServerError;
import com.zoomcare.candidatechallenge.exceptions.EmployeeNotFound;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() throws EmployeeInternalServerError {
        try {
            List<EmployeeEntity> employees = employeeRepository.findAll();
            return employees.stream().map(this::getEmployeeFromEntity).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
            throw new EmployeeInternalServerError(String.format("Unexpected error occurred getting all employees. %s", ex.getLocalizedMessage()));
        }
    }

    @Override
    public Employee getEmployeeById(Long employeeId) throws EmployeeNotFound, EmployeeInternalServerError {
        try {
            EmployeeEntity employee = employeeRepository.findEmployeeById(employeeId);
            if (employee == null) {
                throw new EmployeeNotFound(String.format("No entry was found for employee %s", employeeId));
            }
            return getEmployeeFromEntity(employee);
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
            throw new EmployeeInternalServerError(String.format("Unexpected error occurred while getting employee by id %d. %s", employeeId, ex.getLocalizedMessage()));
        }
    }

    private Employee getEmployeeFromEntity(EmployeeEntity entity) {

        return Employee.builder()
                .id(entity.getId())
                .title(getPropertyValue(PropertyKey.TITLE, entity.getEmployeeProperties()))
                .region(getPropertyValue(PropertyKey.REGION, entity.getEmployeeProperties()))
                .supervisor(getSupervisor(entity))
                .build();

    }

    private String getPropertyValue(PropertyKey key, Set<PropertyEntity> properties) {
        if (properties == null) return null;
        for (PropertyEntity property: properties) {
            if (property.getKey().equals(key.toString())) {
                return property.getValue();
            }
        }
        return null;
    }

    private Supervisor getSupervisor(EmployeeEntity entity) {
        if (entity.getSupervisorId() == null) return null;
        return Supervisor.builder()
                .id(entity.getSupervisorId())
                .title(getPropertyValue(PropertyKey.TITLE, entity.getSupervisorProperties()))
                .region(getPropertyValue(PropertyKey.REGION, entity.getSupervisorProperties()))
                .build();
    }
}
