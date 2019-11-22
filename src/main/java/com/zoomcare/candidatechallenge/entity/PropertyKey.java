package com.zoomcare.candidatechallenge.entity;

public enum PropertyKey {
    TITLE("title"),
    REGION("region");
    private String key;

    PropertyKey(String key) {
        this.key = key;
    }

    public String toString() {
        return key;
    }
}
