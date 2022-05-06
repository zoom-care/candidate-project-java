package com.zoomcare.candidatechallenge.entities;

import lombok.Data;

@Data
public class Property {
    String key;
    String value;

    public Property(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
