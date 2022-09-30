package com.zoomcare.candidatechallenge.service;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.repo.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyService {
    public final PropertyRepo propertiesRepo;

    @Autowired
    public PropertyService(PropertyRepo propertiesRepo) {
        this.propertiesRepo = propertiesRepo;
    }

    public List<Property> topProperties(){
        List<Property> propertiesList = propertiesRepo.findAll();
        return propertiesList;
    }
}
