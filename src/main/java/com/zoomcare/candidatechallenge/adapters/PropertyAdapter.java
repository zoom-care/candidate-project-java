package com.zoomcare.candidatechallenge.adapters;

import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.model.dto.PropertyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyAdapter {

    public static PropertyDto map(Property property) {
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setKey(property.getId().getKey());
        propertyDto.setValue(property.getValue());
        return propertyDto;
    }

    public static List<PropertyDto> map(List<Property> properties) {
        List<PropertyDto> propertiesList = properties.stream()
                .map(property -> PropertyAdapter.map(property)).collect(Collectors.toList());
        return propertiesList;
    }
}
