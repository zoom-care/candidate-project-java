package com.zoomcare.candidatechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DBUtils {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Integer> getTopLevelEmployees() {
        return jdbcTemplate.query("select * from employee where supervisor_id is null", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("id");
            }
        });
    }

    public List<Integer> getDirectReports(Integer id) {
        List<Integer> directReportList = jdbcTemplate.query("select * from employee where supervisor_id=" + id, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("id");
            }
        });
        return directReportList;
    }

    public Map<Integer, List<List<String>>> getEmployeesProperties(List<Integer> id) {
        String inSql = String.join(",", id.stream().map(Object::toString).collect(Collectors.toList()));
        Map<Integer, List<List<String>>> map = new HashMap<>();
        jdbcTemplate.query(String.format("select * from property where employee_id in (%s)", inSql), new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                List<String> l = new ArrayList<>();
                int id = resultSet.getInt("employee_id");
                if (!map.containsKey(id))
                    map.put(id, new ArrayList<>());
                l.add(resultSet.getString("key"));
                l.add(resultSet.getString("value"));
                List<List<String>> properties = map.get(id);
                properties.add(l);
                map.put(id, properties);
                return 0;
            }
        });
        return map;
    }
}
