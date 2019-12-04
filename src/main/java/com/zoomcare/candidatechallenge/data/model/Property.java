package com.zoomcare.candidatechallenge.data.model;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table("property")
public class Property {

    @EmbeddedId
    private PropertyKey propertyKey;


    @ManyToOne
    @JoinColumn(name="employee_id", insertable = false, updatable = false)
    private Employee employee;


    @Column("value")
    private String value;


    public Property() {
    }

    public Property(PropertyKey propertyKey, String value) {
        this.propertyKey = propertyKey;
        this.value = value;
    }

    public PropertyKey getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(PropertyKey propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Property{" +
                "propertyKey=" + propertyKey +
                ", value='" + value + '\'' +
                '}';
    }

    @Embeddable
    public static class PropertyKey implements Serializable {

        @Column("employee_id")
        private Long employee_Id;

        @Column("key")
        private String key;

    }

}
