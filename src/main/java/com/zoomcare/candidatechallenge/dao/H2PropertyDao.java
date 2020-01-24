package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("H2PropertyDao")
public class H2PropertyDao implements PropertyDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2PropertyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Property> getPropertyByEmployeeId(Long id) {
        final String sql = "select employee_id, key, value from property where employee_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, (resultSet, i) -> {
            return new Property(resultSet.getLong("employee_id"),
                    resultSet.getString("key"),
                    resultSet.getString("value"));
        });
    }
}
