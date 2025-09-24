package com.superterminais.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LEGAL")
public class LegalPerson extends Company {

    @Column(name = "corporate_name") // Razão Social
    private String corporateName;

    @Column(unique = true)
    private String cnpj;

    // Getters and Setters

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}