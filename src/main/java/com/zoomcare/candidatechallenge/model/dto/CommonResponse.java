package com.zoomcare.candidatechallenge.model.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zoomcare.candidatechallenge.model.enums.ResponseCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonPropertyOrder(value = {"code", "message", "data"})
public class CommonResponse<T> {
    private final ResponseCode code;
    private final String message;
    private final T data;

    public CommonResponse(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    @JsonGetter("code")
    public String getJsonCode() {
        return code.toString();
    }

    @JsonIgnore
    public ResponseCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
