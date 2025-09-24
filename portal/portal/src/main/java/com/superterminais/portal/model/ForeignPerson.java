package com.superterminais.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FOREIGN")
public class ForeignPerson extends Company {

    @Column(name = "corporate_name") // Razão Social
    private String corporateName;

    @Column(name = "foreign_id", unique = true) // Identificador Estrangeiro
    private String foreignId;

    // Getters and Setters

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }
}