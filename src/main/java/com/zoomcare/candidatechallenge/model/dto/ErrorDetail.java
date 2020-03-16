package com.zoomcare.candidatechallenge.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ErrorDetail
{
    @Builder.Default
    private final Date timestamp;
    @Builder.Default
    private final int status;
    @Builder.Default
    private final String message;
    private String detail;
}