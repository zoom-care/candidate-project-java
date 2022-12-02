package com.zoomcare.candidatechallenge.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

//Data Access Object class for Employee table
@Repository("Properties")
public class PropertiesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<List<String>> getEmployeeProperties(Long id){
        String PROPERTIES_QUERY  = "Select * from property where employee_id="+id;
        return jdbcTemplate.query(PROPERTIES_QUERY ,ROW_MAPPER_PROP);
    }

    private static final RowMapper<List<String>> ROW_MAPPER_PROP =
            (rs, rowNum) ->
                    Arrays.asList(rs.getString("key"), rs.getString("value"));
}
