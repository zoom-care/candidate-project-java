package com.zoomcare.candidatechallenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoomcare.candidatechallenge.config.Constants;
import com.zoomcare.candidatechallenge.dao.EmployeeDao;
import com.zoomcare.candidatechallenge.dao.PropertyDao;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.exception.EmployeeProcessingException;
import com.zoomcare.candidatechallenge.exception.PropertyProcessingException;
import com.zoomcare.candidatechallenge.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Processes a rest call which was made for a specific employee id, to gather the employee object for that employee
     * including all of that employee's direct reports.   This work is accomplished in three calls to data access
     * object which
     * 1) Add the given employee to the employee map.
     * 2) Add all of the direct reports for the given employee.
     * 3) Add the employee properties to the given employee and each of the direct report employees.
     *
     * @param employeeId The ID of the employee that we are generating a record for
     * @return A String that represents the JSON object for the employee, included the deeply nested direct report
     *         employees.
     * @throws EmployeeProcessingException  If processing the employee and direct reports fails.
     * @throws EmployeeNotFoundException  If the employee id provide is not defined in the employee table.
     * @throws PropertyProcessingException  If processing properties for either the employee or their direct reports fails.
     */
    public String processIndividualEmployee(Long employeeId) throws EmployeeProcessingException, EmployeeNotFoundException, PropertyProcessingException {
        logger.debug("Enter processIndividualEmployee for employee " + employeeId);

        // Map that will contain the employees that will be part of the output.  This is keyed on employee id.
        HashMap<Long, Employee> employeeMap = new HashMap<>();

        // Add the given employeeId to the employee map
        employeeDao.createBaseEmployeeEntity(employeeId, employeeMap);

        // Add the direct-reports for the employeeId
        employeeDao.assignDirectReportListForSupervisorId(employeeId, employeeMap);

        // Add the employee properties to the given employee and each of the direct report employees.
        propertyDao.addPropertiesToEmployee(employeeMap);

        // return a string representation of the employee and direct-reports in JSON.
        try {
            return objectMapper.writeValueAsString(employeeMap.get(employeeId));
        } catch (JsonProcessingException e) {
            logger.error("Failed to create output for employee {}", employeeId, e);
            throw new EmployeeProcessingException(
                    StringUtils.replace(Constants.UNABLE_TO_CREATE_RESPONSE, "{0}", employeeId.toString()),
                    e);
        }
    }

    /**
     * Processes a rest call to gather all top level employees and their direct reports.   A top-level employee
     * is defined as an employee whos supervisor ID value is 'null'.
     *
     * This work is done in two steps.
     * 1) Add all of the top level employees and their direct reports.
     * 2) Add the employee properties to the top level employees and each of the direct report employees.
     *
     * @return A String that represents the JSON object for the list of top-level employees and their direct reports.
     *         This will be a deeply nested list of employees and their direct reports.
     * @throws EmployeeProcessingException If the employee list could not be processed successfully.
     * @throws PropertyProcessingException If the properties could not be successfully added to the employees.
     */
    public String processAllEmployees() throws EmployeeProcessingException, PropertyProcessingException {
        logger.debug("Enter processAllEmployees");

        // Map that will contain the employees that will be part of the output.  This is keyed on employee id.
        HashMap<Long, Employee> employeeMap = new HashMap<>();

        // Create the list of all top-level employees and their direct reports.   This will return a list
        // of those employees with a null supervisor id value.
        List<Long> nullSupervisorIdList = employeeDao.assignDirectReportListForNull(employeeMap);

        // Add the employee properties to the top-level employee(s) and each of the direct report employees.
        propertyDao.addPropertiesToEmployee(employeeMap);

        // create a list that contains only the top-level employees.
        List<Employee> nullSupervisorEmployeeList = new ArrayList<>();
        for (Long id: nullSupervisorIdList) {
            nullSupervisorEmployeeList.add(employeeMap.get(id));
        }

        // Create a JSON String that represents the list of top-level employees and their direct reports, displayed
        // in a deeply nested structure.
        try {
            return objectMapper.writeValueAsString(nullSupervisorEmployeeList);
        } catch (JsonProcessingException e) {
            logger.error(Constants.UNABLE_TO_CREATE_RESPONSE_ALL);
            throw new EmployeeProcessingException(
                    Constants.UNABLE_TO_CREATE_RESPONSE_ALL, e.getCause());
        }
    }

}
