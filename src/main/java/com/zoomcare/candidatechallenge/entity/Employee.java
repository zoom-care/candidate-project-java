package com.zoomcare.candidatechallenge.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    private Long id;
    private Long supervisor_id;

    public Employee() {}

    public Employee(Long supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(Long supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", supervisor_id=" + supervisor_id +
                '}';
    }
}
