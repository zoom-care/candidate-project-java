package com.zoomcare.candidatechallenge.mappers;

import com.zoomcare.candidatechallenge.entities.Property;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyRowMapper implements RowMapper<Property> {
    @Override
    public Property mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Property(resultSet.getString("key"), resultSet.getString("value"));
    }
}
