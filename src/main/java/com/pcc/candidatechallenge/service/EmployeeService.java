package com.pcc.candidatechallenge.service;

import com.pcc.candidatechallenge.dao.EmployeeDao;
import com.pcc.candidatechallenge.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("employeeService")
public class EmployeeService
{
    EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    public List<Employee> getTopLevelEmployeesArray(boolean recursiveLoad)
    {
       return employeeDao.getTopLevelEmployees(recursiveLoad);
    }

    public Employee getEmployee(long ID, boolean recursiveLoad)
    {
        return employeeDao.getEmployee(ID, recursiveLoad);
    }
}
