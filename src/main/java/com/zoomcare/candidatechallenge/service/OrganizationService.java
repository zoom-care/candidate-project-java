package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.mapper.EmployeeMapper;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles organization operations
 */
@Service
public class OrganizationService {

    private EmployeeRepository employeeRepository;

    public OrganizationService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<EmployeeDTO> findById(Long id) {
        return employeeRepository.findById(id).map(EmployeeMapper.INSTANCE::employeeToEmployeeDTO);
    }

    public List<EmployeeDTO> getAllTopLevelsEmployees(){
        return employeeRepository.findAllBySupervisorIdIsNull().stream()
                .map(EmployeeMapper.INSTANCE::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }
}
