package com.zoomcare.candidatechallenge.data.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
  
  private Long id;
  
  @Getter(AccessLevel.NONE)
  private Map<String, String> properties;

  private List<EmployeeDTO> directReports;
  
  @JsonAnyGetter
  public Map<String, String> getProperties() {
    return this.properties;
  }
}
