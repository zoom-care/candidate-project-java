package com.zoomcare.candidatechallenge.dao;

import com.zoomcare.candidatechallenge.model.Property;

import java.util.List;

public interface PropertyDao {

    List<Property> getPropertyByEmployeeId(Long id);
}
