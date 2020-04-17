package com.pcc.candidatechallenge.dao;

import com.pcc.candidatechallenge.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao
{
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private List<Employee> query(String sql, Map<String, Object> params, boolean recursiveLoad)
    {
        List<Employee> employeesArray = new ArrayList<>();
        Employee employee = null, employeeReport = null;
        Long eId = 0L, sId = 0L, rId = 0L;
        String pKey = "", rPKey = "";
        String pValue = "", rPValue = "";
        Long prevEId = 0L, prevRId = 0L;
        String prevPKey = "", prevRPKey = "";

        List<EmployeeMapping> result = namedParameterJdbcTemplate.query(sql, params, new EmployeeMappingMapper());
        for (EmployeeMapping m : result)
        {
            eId = m.getEId();
            if (eId == null) eId = 0L;
            sId = m.getSId();
            if (sId == null) sId = 0L;
            rId = m.getRId();
            if (rId == null) rId = 0L;
            pKey = m.getPKey();
            if (pKey == null) pKey = "";
            rPKey = m.getRPKey();
            if (rPKey == null) rPKey = "";
            pValue = m.getPValue();
            if (pValue == null) pValue = "";
            rPValue = m.getRPValue();
            if (rPValue == null) rPValue = "";

            if (!eId.equals(prevEId))
            {
                employee = new Employee();
                employee.setId(eId);
                employee.setSupervisorId(sId);
                employeesArray.add(employee);
                prevRId = 0L;
                prevPKey = "";
                prevRPKey = "";
            }
            if (!rId.equals(prevRId) && rId != 0L)
            {
                if (recursiveLoad == true)
                {
                    employeeReport = getEmployee(rId, recursiveLoad);
                } else
                {
                    employeeReport = new Employee();
                    employeeReport.setId(rId);
                    employeeReport.setSupervisorId(eId);
                }
                employee.getDirectReportsArray().add(employeeReport);
                prevRPKey = "";
            }
            if (!pKey.equals(prevPKey) && !pKey.isEmpty())
                employee.getProperties().put(pKey, pValue);
            if (!rPKey.equals(prevRPKey) && !rPKey.isEmpty())
                employeeReport.getProperties().put(rPKey, rPValue);
            prevEId = eId.longValue();
            prevRId = rId.longValue();
            prevPKey = pKey;
            prevRPKey = rPKey;
        }
        return employeesArray;
    }

    public List<Employee> getTopLevelEmployees(boolean recursiveLoad)
    {
        String sql = "select e.id as e_id, e.supervisor_id as s_id, r.id as r_id, p.key as p_key, p.value as p_value, rp.key as rp_key, rp.value as rp_value " +
                     "from  (employee as e left join employee as r on r.supervisor_id = e.id left join property as rp on rp.employee_id = r.id left join property as p on e.id = p.employee_id) " +
                     "where e.supervisor_id is null " +
                     "order by e_id, r_id, p_key, rp_key";
        Map<String, Object> params = new HashMap<>();
        return query(sql, params, recursiveLoad);
    }

    public Employee getEmployee(long Id, boolean recursiveLoad)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("id", Id);
        String sql = "select e.id as e_id, e.supervisor_id as s_id, r.id as r_id, p.key as p_key, p.value as p_value, rp.key as rp_key, rp.value as rp_value " +
                     "from  (employee as e left join employee as r on r.supervisor_id = e.id left join property as rp on rp.employee_id = r.id left join property as p on e.id = p.employee_id) " +
                     "where e.id =:id " +
                     "order by e_id, r_id, p_key, rp_key";
        List<Employee> employeesArray = query(sql, params, recursiveLoad);

        for (Employee e : employeesArray)
        {
            if (e.getId() == Id)
                return e;
        }
        return null;
    }

    private static final class EmployeeMappingMapper implements RowMapper<EmployeeMapping>
    {
        public EmployeeMapping mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            EmployeeMapping mapping = new EmployeeMapping();
            mapping.setEId(rs.getLong("e_id"));
            mapping.setSId(rs.getLong("s_id"));
            mapping.setRId(rs.getLong("r_id"));
            mapping.setPKey(rs.getString("p_key"));
            mapping.setPValue(rs.getString("p_value"));
            mapping.setRPKey(rs.getString("rp_key"));
            mapping.setRPValue(rs.getString("rp_value"));
            return mapping;
        }
    }
}