package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.config.Constants;
import com.zoomcare.candidatechallenge.exception.PropertyProcessingException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.zoomcare.candidatechallenge.config.Constants.*;

@Repository("propertyDao")
public class PropertyDao {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    /**
     * Given the a collection of Employee objects, query the Property table to gather all of the employee's
     * properties.  Each property is then added to its employee object.
     *
     * @param idEmployeeMap The collection of employee objects to which the properties are to be added.
     * @throws PropertyProcessingException If unable to complete the request
     */
    public void addPropertiesToEmployee(Map<Long, Employee> idEmployeeMap) throws PropertyProcessingException {
        logger.debug("Enter addPropertiesToEmployee");

        try {
            // get the list of employee IDs that will be used in the query
            List<Long> ids = Arrays.asList(idEmployeeMap.keySet().toArray(new Long[idEmployeeMap.size()]));

            // query the property table to get the properties associated with the list of employee IDs
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(IDS_TO_FETCH, ids);
            namedParameterJdbcTemplate.query(
                    EMPLOYEE_PROPERTIES_QUERY,
                    params,
                    new ResultSetExtractor<Void>() {
                        @Override
                        public Void extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                            Employee employee = null;
                            while (resultSet.next()) {

                                // add each property to its assigned employee
                                Long employeeId = resultSet.getLong(EMPLOYEE_ID);
                                Property property = new Property(resultSet.getString(KEY),
                                                                 resultSet.getString(VALUE));

                                // since the query orders by employee_id, we can do a small
                                // optimizion and avoid pulling employee from the employee map
                                // for each property that is returned.
                                if (null == employee || employee.getId() != employeeId) {
                                    employee = idEmployeeMap.get(employeeId);
                                }
                                employee.getProperties().add(property);
                            }
                            return null;
                        }
                    });
        } catch (Exception ex) {
            logger.error("Exception processing employee properties", ex);
            throw new PropertyProcessingException(Constants.UNABLE_TO_PROCESS_PROPERTIES);
        }
    }
}
