package com.zoomcare.candidatechallenge.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {
    BigInteger id;
    BigInteger supervisorId;
    List<EmployeeDto> employees = new ArrayList<>();
    List<PropertyDto> properties = new ArrayList<>();
}
