package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.config.Constants;
import com.zoomcare.candidatechallenge.dao.EmployeeDao;
import com.zoomcare.candidatechallenge.dao.PropertyDao;
import com.zoomcare.candidatechallenge.exception.EmployeeIdInvalidException;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.exception.EmployeeProcessingException;
import com.zoomcare.candidatechallenge.exception.PropertyProcessingException;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.zoomcare.candidatechallenge.config.Constants.EMPLOYEE_ID_MUST_BE_NUMBER;

@RestController
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Endpoint which provides the employee record for a given employee ID.   The employee record
     * will contain the employee's id, their supervisor's id, properties associated with the employee,
     * and the list of direct reports for the employee.    The direct reports will themselves be in the
     * form of an employee record, providing a hierarchical view of the reports.
     *
     * @param employeeIdParam The employee ID that is to be looked up.
     * @return A JSON String representing the employee record for a given employee ID, or if there is
     * an error, details on the error including a user error message.
     *
     * Successful rest call will return a HTTP status code of 200.
     * Failures do to internal processing errors treturn a HTTP status code of 500
     * Invalid requests (for instance, invalid employee ID format, employee ID does not exist) returns
     * a HTTP status code of 400.
     */
    @GetMapping(value= Constants.BASE_URL + "/{employeeIdParam}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSingleEmployee(@PathVariable String employeeIdParam)
            throws EmployeeProcessingException, EmployeeNotFoundException, EmployeeIdInvalidException, PropertyProcessingException {
        logger.debug("Enter getSingleEmployee for employee {}", employeeIdParam);

        // check if employeeId is valid (is a Long value)
        Long employeeId = null;
        try {
            employeeId = new Long(employeeIdParam);
        } catch (java.lang.NumberFormatException nfex) {
            logger.warn(EMPLOYEE_ID_MUST_BE_NUMBER);
            throw new EmployeeIdInvalidException(EMPLOYEE_ID_MUST_BE_NUMBER);
        }

        return employeeService.processIndividualEmployee(employeeId);
    }

    /**
     * Endpoint which provides a list of all the employee records for the organization.  The employee record
     * will contain the employee's id, their supervisor's id, properties associated with the employee,
     * and the list of direct reports for the employee.    The direct reports will themselves be in the
     * form of an employee record, providing a hierarchical view of the reports.
     *
     * Successful rest call will return a HTTP status code of 200.
     * Failures do to internal processing errors treturn a HTTP status code of 500
     *
     * @return A list of top level employees including all of their direct reports, presented in a deeply
     * nested structure.
     */
    @GetMapping(value=Constants.BASE_URL,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllEmployees() throws EmployeeProcessingException, PropertyProcessingException {
        logger.debug("Enter getAllEmployees");

        return employeeService.processAllEmployees();
    }
}
