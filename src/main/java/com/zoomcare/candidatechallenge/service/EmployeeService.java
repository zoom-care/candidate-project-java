package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.mapper.EmployeeMapper;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public EmployeeDto getEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (!optionalEmployee.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Employee %s was not found", employeeId));
        }
        return EmployeeMapper.INSTANCE.employeeToEmployeeDto(optionalEmployee.get());
    }

    public List<EmployeeDto> getTopEmployee(Pageable pageable) {

        return employeeRepository.findAll(pageable)
                .stream()
                .map(EmployeeMapper.INSTANCE::employeeToEmployeeDto)
                .collect(Collectors.toList());
    }

}
