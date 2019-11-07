package com.zoomcare.candidatechallenge.mapper;

import com.zoomcare.candidatechallenge.model.dto.EmployeePropertyDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

@Component
public class EmployeePropertyMapper {
  public static EmployeePropertyDto map(final ResultSet resultSet) throws SQLException {
    if (resultSet == null) {
      throw new IllegalArgumentException("Null ResultSet provided to EmployeeMapper");
    }

    return EmployeePropertyDto.builder()
        .id(resultSet.getLong(1))
        .supervisorId(resultSet.getLong(2))
        .employeeId(resultSet.getLong(3))
        .key(resultSet.getString(4))
        .value(resultSet.getString(5))
        .build();
  }
}
