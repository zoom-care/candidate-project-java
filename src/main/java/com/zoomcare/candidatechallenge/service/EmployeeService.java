package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.entity.EmployeeEntity;
import com.zoomcare.candidatechallenge.model.EmployeeModel;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeModel getEmployee(Long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            EmployeeEntity e = employee.get();
            EmployeeModel model = EmployeeModel.parseFrom(e);
            return model;
        } else {
            return EmployeeModel.EMPTY;
        }
    }

    public List<EmployeeModel> getTopEmployees(){
        List<EmployeeModel> topEmployees = new ArrayList<>();
        List<EmployeeEntity> topEntityEmployees = employeeRepository.findBySupervisorIsNull();

        if(topEntityEmployees!=null && !topEntityEmployees.isEmpty()){
            for(EmployeeEntity entity : topEntityEmployees){
                topEmployees.add(EmployeeModel.parseFrom(entity));
            }
        }
        return topEmployees;
    }
}
