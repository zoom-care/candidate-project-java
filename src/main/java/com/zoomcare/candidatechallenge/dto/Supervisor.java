package com.zoomcare.candidatechallenge.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Supervisor {
    Long id;
    String title;
    String region;
}
