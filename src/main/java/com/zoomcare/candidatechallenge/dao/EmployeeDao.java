package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.config.Constants;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.exception.EmployeeProcessingException;
import com.zoomcare.candidatechallenge.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.zoomcare.candidatechallenge.config.Constants.*;

@Repository("employeeDao")
public class EmployeeDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Query the employee table gathering all top-level employees and all of their direct reports.   A top-level
     * employee is one which has a supervisor_id value of 'null'.
     *
     * The query will run recursively, processing each employee in the order of the employee's level (top-level
     * has a level = 0, their reports level = 1, their report's reports level = 2, etc...).   This top-down approach
     * provides the means for setting each employee's direct reports with the actual employee object of their
     * direct report.
     *
     * @param idEmployeeMap Map that holds all Employee objects, keyed on the employee's ID value.   This method
     *                      will put employee objects into the map based on the results of the query.
     * @return The list of Employees that have a 'null' value for supervisor.  These are defined as top-level employees.
     * @throws EmployeeProcessingException if gathering the employees fails.
     */
    public List<Long> getDirectReportListForNull(Map<Long, Employee> idEmployeeMap) throws EmployeeProcessingException {
        logger.debug("Enter getDirectReportListForNull");

        try {
            // query the employee table, getting the list of top-level and all direct report employees
            return jdbcTemplate.query(
                    Constants.TOP_LEVEL_QUERY,
                    new ResultSetExtractor<List<Long>>() {
                        public List<Long> extractData(ResultSet rs) throws SQLException {
                            List<Long> nullSupervisors = new ArrayList<>();
                            while (rs.next()) {
                                Long id = rs.getLong(ID);
                                Long supervisorId = rs.getLong(SUPERVISOR_ID);

                                // ensure supervisor_id remains null for top-level
                                supervisorId = supervisorId == 0 ? null : supervisorId;

                                // create employee and add to the employee map
                                Employee employee = new Employee(id, supervisorId);
                                idEmployeeMap.put(employee.getId(), employee);

                                if (null == supervisorId) {
                                    // we will return the list of top-level employees
                                    nullSupervisors.add(employee.getId());
                                } else {
                                    // if not top-level, we add the employee to their supervisor's direct-report list.
                                    Employee supervisor = idEmployeeMap.get(supervisorId);
                                    supervisor.getDirectReports().add(employee);
                                }
                            }
                            return nullSupervisors;
                        }
                    });
        } catch (Exception ex) {
            logger.error("Exception processing employee properties", ex);
            throw new EmployeeProcessingException(UNABLE_TO_PROCESS_EMPLOYEE, ex.getCause());
        }
    }

    /**
     * Query the employee table for a specific employee id, then using a recursive query, gather all of that employee's
     * direct reports.
     *
     * The query orders the employees by their relative management level.  The employee id provided is considered level = 0,
     * their direct reports level = 1, and the direct reports for them level = 2 and so on.   This top-down approach
     * provides the means for setting each employee's direct reports with the actual employee object of their
     * direct report.
     *
     * @param employeeId  The employee ID being queried.
     * @param idEmployeeMap Map that holds all Employee objects, keyed on the employee's ID value.   This method
     *                      will put employee objects into the map based on the results of the query.
     * @throws EmployeeProcessingException if gathering employees fails.
     */
    public void getDirectReportListForSupervisorId(Long employeeId, Map<Long, Employee> idEmployeeMap)
            throws EmployeeProcessingException {
        logger.debug("Enter getDirectReportListForSupervisorId for employeeId {}", employeeId);

        try {
            // query the employee table
            jdbcTemplate.query(
                    Constants.EMP_QUERY,
                    createPreparedStatementSetter(employeeId),
                    new ResultSetExtractor<Void>() {
                        public Void extractData(ResultSet rs) throws SQLException {
                            while (rs.next()) {
                                Long id = rs.getLong(ID);
                                Long supervisorId = rs.getLong(SUPERVISOR_ID);

                                // create a new employee and add to employee map.
                                Employee employee = new Employee(id, supervisorId);
                                idEmployeeMap.put(employee.getId(), employee);

                                // Add this new employee to their supervisor's direct report list
                                Employee supervisor = idEmployeeMap.get(supervisorId);
                                supervisor.getDirectReports().add(employee);
                            }
                            return null;
                        }
                    });
        } catch (Exception ex) {
            logger.error("Exception processing employee properties", ex);
            throw new EmployeeProcessingException(UNABLE_TO_PROCESS_EMPLOYEE, ex.getCause());
        }
    }

    /**
     * Add a specific employee to the employee map.
     *
     * @param employeeId The employee id of the employee.
     * @param idEmployeeMap Map that holds all Employee objects, keyed on the employee's ID value.   This method
     *                      will put employee objects into the map based on the results of the query.
     * @throws EmployeeNotFoundException if processing employee fails
     */
    public void createBaseEmployeeEntity(Long employeeId, HashMap<Long, Employee> idEmployeeMap) throws EmployeeNotFoundException {
        logger.debug("Enter createBaseEmployeeEntity for employee ID {}", employeeId);

        Employee employee = null;

        // query for a specific employee
        try {
            employee = jdbcTemplate.query(
                    Constants.ONE_EMPLOYEE_QUERY,
                    createPreparedStatementSetter(employeeId),
                    new ResultSetExtractor<Employee>() {
                        public Employee extractData(ResultSet rs) throws SQLException {
                            Employee employeeResult = null;
                            rs.last();
                            Long id = rs.getLong(ID);
                            Long supervisorId = rs.getLong(SUPERVISOR_ID);

                            // if this is a top-level employee, keep their supervisor id as null.
                            supervisorId = supervisorId == 0 ? null : supervisorId;
                            employeeResult = new Employee(id, supervisorId);
                            return employeeResult;
                        }
                    });
        } catch (Exception ex) {
            String msg = StringUtils.replace(EMPLOYEE_ID_NOT_FOUND, "{0}", employeeId.toString());
            logger.warn(msg);
            logger.debug(msg,ex);
            throw new EmployeeNotFoundException(msg);
        }

        // add the employee to the employee map
        idEmployeeMap.put(employee.getId(), employee);
    }

    /**
     * Method for generating a new PreparedStatementSetter.
     *
     * @param employeeId the employee id that is used in a query as a positional parameter.
     * @return The PreparedStatementSetter to be used by the JdbcTemplate.
     */
    private PreparedStatementSetter createPreparedStatementSetter(Long employeeId) {
        logger.debug("Enter createPreparedStatementSetter for {}", employeeId);

        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, employeeId.toString());
            }
        };
    }

}
