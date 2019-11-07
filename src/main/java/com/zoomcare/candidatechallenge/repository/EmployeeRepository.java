package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.mapper.EmployeePropertyMapper;
import com.zoomcare.candidatechallenge.model.dto.EmployeePropertyDto;
import com.zoomcare.candidatechallenge.util.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
  private static final Logger LOG = LoggerFactory.getLogger(EmployeeRepository.class);

  private ResultSet resultSet = null;
  private Connection connection = null;
  private PreparedStatement pstmt = null;

  /**
   * Returns an Employee by ID.
   *
   * @param employeeId Employee ID
   * @return EmployeeDTO
   */
  public List<EmployeePropertyDto> getEmployeeById(Long employeeId) {
    List<EmployeePropertyDto> resultList = new ArrayList();
    Connection connection = DatabaseConnector.getInstance().getConnection();

    try (PreparedStatement preparedStatement =
        connection.prepareStatement(
            "SELECT * FROM EMPLOYEE, PROPERTY WHERE ID = PROPERTY.EMPLOYEE_ID AND ID = ? OR SUPERVISOR_ID = ?")) {
      preparedStatement.setLong(1, employeeId);
      preparedStatement.setLong(2, employeeId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        resultList.add(EmployeePropertyMapper.map(resultSet));
      }

    } catch (SQLException e) {
      LOG.error("Error executing statement. " + e.getMessage());
    }

    return resultList;
  }

  /**
   * Returns all Employees who have no supervisor.
   *
   * @return List<EmployeeDTO>
   */
  public List<EmployeePropertyDto> getTopLevelEmployees() {
    List<EmployeePropertyDto> resultList = new ArrayList();
    Connection connection = DatabaseConnector.getInstance().getConnection();

    try (Statement statement = connection.createStatement()) {
      resultSet =
          statement.executeQuery(
              "SELECT * FROM EMPLOYEE, PROPERTY WHERE EMPLOYEE.ID = PROPERTY.EMPLOYEE_ID AND SUPERVISOR_ID IS NULL");

      while (resultSet.next()) {
        resultList.add(EmployeePropertyMapper.map(resultSet));
      }

    } catch (SQLException e) {
      LOG.error("Error executing statement. " + e.getMessage());
    }

    return resultList;
  }
}
