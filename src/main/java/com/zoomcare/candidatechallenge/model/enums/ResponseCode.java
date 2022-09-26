package com.zoomcare.candidatechallenge.model.enums;

public enum ResponseCode {
    OPERATION_SUCCESSFUL("challenge-0001"),
    UNEXPECTED_ERROR("challenge-0002"),
    DATA_NOT_FOUND("challenge-0003");

    private final String code;

    ResponseCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
