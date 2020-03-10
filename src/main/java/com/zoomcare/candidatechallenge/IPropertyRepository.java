package com.zoomcare.candidatechallenge;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IPropertyRepository {
    public List<PropertyDO> getByEmployee(int employeeId) throws Exception;
}