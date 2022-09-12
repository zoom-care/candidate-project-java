package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.exception.NotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.output.EmployeeDTO;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    @Qualifier("ModelMapper")
    private ModelMapper modelMapper;

    public List<EmployeeDTO> getAllTopLevelEmployees(){
        return modelMapper.map(employeeRepository.findBySupervisorIdIsNull(), new TypeToken<List<EmployeeDTO>>() {}.getType());
    }

    public EmployeeDTO getById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee not found for id: %s", id)));
        return modelMapper.map(employee, EmployeeDTO.class);
    }
}