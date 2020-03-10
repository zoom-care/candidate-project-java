package com.zoomcare.candidatechallenge;

import java.util.ArrayList;
import java.util.List;

public class PropertyMapper {

    /**
     * 
     * @param property Data object to map
     * @return Property transfer object mapped from given data object
     */
    public static Property map(PropertyDO property) {
        return new Property(property.getEmployeeId(), property.getKey(), property.getValue());
    }

    /**
     * 
     * @param properties Data object to map
     * @return Property transfer object mapped from given data object
     */
    public static List<Property> map(List<PropertyDO> properties) {
        List<Property> mappedProperties = new ArrayList<>();
        properties.forEach(property -> mappedProperties.add(map(property)));
        return mappedProperties;
    }
}