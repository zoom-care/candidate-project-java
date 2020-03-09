package com.zoomcare.candidatechallenge;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    /**
     * 
     * @param employee Data object to map
     * @return Employee transfer object mapped from given data object
     */
    public static Employee map(EmployeeDO employee) {
        return new Employee(employee.getId(), employee.getSupervisorId());
    }

    /**
     * 
     * @param employees Data object to map
     * @return Employee transfer object mapped from given data object
     */
    public static List<Employee> map(List<EmployeeDO> employees) {
        List<Employee> mappedEmployees = new ArrayList<>();
        employees.forEach(employee -> mappedEmployees.add(map(employee)));
        return mappedEmployees;
    }
}