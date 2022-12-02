package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dao.EmployeeDao;
import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeDto getEmployee(Long id){
        return employeeDao.getEmployeeById(id);

    }
   public List<EmployeeDto> getTopLevelEmployeeList(){
        return employeeDao.getTopLevelEmployees();
    }

}
