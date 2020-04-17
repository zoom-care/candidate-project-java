package com.pcc.candidatechallenge.dao;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapping
{
    private Long eId;
    private Long sId;
    private Long rId;
    private String pKey;
    private String pValue;
    private String rPKey;
    private String rPValue;

    public void setEId(Long eId)
    {
        this.eId = eId;
    }
    public long getEId()
    {
        return this.eId;
    }
    public void setSId(Long sId)
    {
        this.sId = sId;
    }
    public long getSId()
    {
        return this.sId;
    }
    public void setRId(Long rId) { this.rId = rId; }
    public Long getRId()
    {
        return this.rId;
    }
    public void setPKey(String pKey)
    {
        this.pKey = pKey;
    }
    public String getPKey()
    {
        return this.pKey;
    }
    public void setPValue(String pValue) {this.pValue = pValue; }
    public String getPValue()
    {
        return this.pValue;
    }
    public void setRPKey(String rPKey)
    {
        this.rPKey = rPKey;
    }
    public String getRPKey()
    {
        return this.rPKey;
    }
    public void setRPValue(String rPValue) { this.rPValue = rPValue; }
    public String getRPValue()
    {
        return this.rPValue;
    }

    private static final class EmployeeMappingMapper implements RowMapper<EmployeeMapping>
    {
        public EmployeeMapping mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            EmployeeMapping employee = new EmployeeMapping();
            employee.setEId(rs.getLong("e_id"));
            employee.setSId(rs.getLong("s_id"));
            employee.setRId(rs.getLong("r_id"));
            employee.setPKey(rs.getString("p_key"));
            employee.setPValue(rs.getString("p_value"));
            employee.setRPKey(rs.getString("rp_key"));
            employee.setRPValue(rs.getString("rp_value"));
            return employee;
        }
    }
}
