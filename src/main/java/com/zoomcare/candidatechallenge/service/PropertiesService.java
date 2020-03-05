package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.db.Properties;
import com.zoomcare.candidatechallenge.repository.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropertiesService {

    private PropertiesRepository propertiesRepsitory;

    @Autowired
    public PropertiesService(PropertiesRepository propertiesRepsitory) {
        this.propertiesRepsitory = propertiesRepsitory;
    }

    public Map<String, String> getPropertiesByEmployeeId(BigInteger id) {
        List<Properties> properties = propertiesRepsitory.getAllPropertiesByEmployeeId(id);
        Map<String, String> propertiesMap = new HashMap<>();
        for (Properties property: properties) {
            propertiesMap.putIfAbsent(property.getKey(), property.getValue());
        }
        return propertiesMap;
    }
}
