package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.data.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.data.entity.EmployeeEntity;
import com.zoomcare.candidatechallenge.data.entity.EmployeePropertyEntity;
import com.zoomcare.candidatechallenge.data.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {
  
  private final EmployeeRepository employeeRepository;
  
  public List<EmployeeDTO> getTopLevelEmployees() {
    return employeeRepository.findAllBySupervisorIdIsNull().stream()
        .map(e -> this.mapToEmployeeDTO(e, false))
        .collect(Collectors.toList());
  }
  
  public EmployeeDTO getEmployee(final long employeeId) {
    return employeeRepository.findById(employeeId)
        .map(employeeEntity -> mapToEmployeeDTO(employeeEntity, true))
        .orElse(null);
  }
  
  private EmployeeDTO mapToEmployeeDTO(final EmployeeEntity employeeEntity, final boolean includeDirectReports) {
    return EmployeeDTO.builder()
        .id(employeeEntity.getId())
        .properties(mapEmployeeProperties(employeeEntity.getProperties()))
        .directReports(includeDirectReports ? mapToEmployeeDTOs(employeeEntity.getDirectReports()) : Collections.emptyList())
        .build();
  }
  
  private Map<String, String> mapEmployeeProperties(List<EmployeePropertyEntity> employeePropertyEntities) {
    return employeePropertyEntities.stream()
        .collect(Collectors.toMap(EmployeePropertyEntity::getKey, EmployeePropertyEntity::getValue));
  }

  private List<EmployeeDTO> mapToEmployeeDTOs(final List<EmployeeEntity> employeeEntities) {
    return employeeEntities.stream()
        .map(e -> this.mapToEmployeeDTO(e, true))
        .collect(Collectors.toList());
  }
}
