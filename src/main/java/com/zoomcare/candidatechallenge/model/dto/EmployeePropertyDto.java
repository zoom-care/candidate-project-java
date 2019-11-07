package com.zoomcare.candidatechallenge.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class EmployeePropertyDto {
  @NotBlank private Long id;
  private Long supervisorId;
  @NotBlank private Long employeeId;
  @NotBlank private String key;
  @NotBlank private String value;
}
