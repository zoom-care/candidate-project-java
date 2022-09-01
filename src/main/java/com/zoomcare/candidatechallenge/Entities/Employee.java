package com.zoomcare.candidatechallenge.Entities;

import org.springframework.lang.Nullable;

public class Employee {
    private Long id;

    @Nullable
    private Long supervisorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(@Nullable Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Employee(String id, String supervisorId) {
        // todo: this is brittle and needs null checks
        this.id = Long.parseLong(id);
        this.supervisorId = Long.parseLong(supervisorId);
    }
}
