package com.zoomcare.candidatechallenge.mapper;

import com.zoomcare.candidatechallenge.dto.PropertyDTO;
import com.zoomcare.candidatechallenge.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PropertyMapper {

    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    PropertyDTO map(Property property);

    List<PropertyDTO> map(List<Property> properties);
}
