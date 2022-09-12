package com.zoomcare.candidatechallenge.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class PropertiesRowMapper implements RowMapper<List<String>>{

		@Override
		public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
			List<String> s = (Arrays.asList(new String[] {rs.getString("key"),rs.getString("value")}));
			return s;
		}
	}

