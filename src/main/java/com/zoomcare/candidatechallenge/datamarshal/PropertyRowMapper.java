package com.zoomcare.candidatechallenge.datamarshal;

import com.zoomcare.candidatechallenge.model.Property;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyRowMapper implements RowMapper<Property> {
    @Override
    public Property mapRow(ResultSet resultSet, int i) throws SQLException {
        Property prop = new Property();
        String empId = resultSet.getString("employee_id");
        prop.setEmployeeId(new BigInteger(empId));
        prop.setKey(resultSet.getString("key"));
        prop.setValue(resultSet.getString("value"));
        return prop;
    }
}
