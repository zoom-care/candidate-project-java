package com.zoomcare.candidatechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmployeeEntity getById(Long id) {

        String query = "select employee.*, property.* from employee LEFT OUTER JOIN property ON employee.id=property.employee_id";
        return jdbcTemplate.query(query, new ResultSetExtractor<EmployeeEntity>() {
            @Override
            public EmployeeEntity extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                EmployeeEntity employee=null;
                Map<Long, Long> supList = new HashMap<>();
                while(resultSet.next()){
                    Long empId = resultSet.getLong("id");
                    if(employee==null && empId==id){
                        employee = new EmployeeEntity();
                        employee.setId(empId);
                        employee.setSupervisor_id(resultSet.getLong("supervisor_id"));
                    }
                    if(resultSet.getLong("supervisor_id")==id){
                        supList.put(empId, empId);
                    }
                    Long propId = resultSet.getLong("employee_id");
                    if(propId>0 && empId==id){
                        PropertyEntity property = new PropertyEntity();
                        property.setEmployeeId(propId);
                        property.setKey(resultSet.getString("key"));
                        property.setValue(resultSet.getString("value"));
                        if(employee.getEmployeeDetails()!=null){
                            employee.getEmployeeDetails().add(property);
                        }else{
                            employee.setEmployeeDetails(new ArrayList<>());
                            employee.getEmployeeDetails().add(property);
                        }
                    }
                }
                employee.setReportees(new ArrayList<>());
                employee.getReportees().addAll(supList.keySet());
                return employee;
            }
        });
    }

    public List<EmployeeEntity> getAllEmployees() {
        String query = "select employee.*, property.* from employee LEFT OUTER JOIN property ON employee.id=property.employee_id";
        return jdbcTemplate.query(query, new ResultSetExtractor<List<EmployeeEntity>>() {
            @Override
            public List<EmployeeEntity> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<EmployeeEntity> list = new ArrayList<>();
                Map<Long, EmployeeEntity> empMap = new HashMap<>();
                Map<Long, List<Long>> reportMap = new HashMap<>();
                List<PropertyEntity> pList = new ArrayList<>();
                while(resultSet.next()){
                    Long empId = resultSet.getLong("id");
                    if(!empMap.containsKey(empId)){
                        EmployeeEntity emp = new EmployeeEntity();
                        emp.setId(empId);
                        Long supervisor_id = resultSet.getLong("supervisor_id");
                        emp.setSupervisor_id(supervisor_id);
                        if(reportMap.containsKey(supervisor_id)){
                            reportMap.get(supervisor_id).add(empId);
                        }else{
                            List<Long> l = new ArrayList<>();
                            l.add(empId);
                            reportMap.put(supervisor_id, l);
                        }
                        empMap.put(empId, emp);
                    }
                    Long empId2 = resultSet.getLong("employee_id");
                    if(empId2>0) {
                        PropertyEntity property = new PropertyEntity();
                        property.setEmployeeId(empId2);
                        property.setKey(resultSet.getString("key"));
                        property.setValue(resultSet.getString("value"));
                        if(empMap.get(empId2).getEmployeeDetails()!=null){
                            empMap.get(empId2).getEmployeeDetails().add(property);
                        }else{
                            empMap.get(empId2).setEmployeeDetails(new ArrayList<>());
                            empMap.get(empId2).getEmployeeDetails().add(property);
                        }
                    }
                }
                list.addAll(empMap.values());
                for (EmployeeEntity emp:list) {
                    if(emp.getReportees()!=null) {
                        if(reportMap.get(emp.getId())!=null)
                            emp.getReportees().addAll(reportMap.get(emp.getId()));
                    }else{
                        if(reportMap.get(emp.getId())!=null) {
                            emp.setReportees(new ArrayList<>());
                            emp.getReportees().addAll(reportMap.get(emp.getId()));
                        }
                    }

                }
                return list;
            }
        });
    }
}
