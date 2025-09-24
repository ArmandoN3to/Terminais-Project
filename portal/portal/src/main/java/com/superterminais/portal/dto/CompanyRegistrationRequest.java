package com.superterminais.portal.dto;


import com.superterminais.portal.model.enums.CompanyType;
import com.superterminais.portal.model.enums.Profile;

public class CompanyRegistrationRequest {
    // Common fields
    private CompanyType companyType;
    private String tradeName; // Nome Fantasia
    private Profile profile;
    private boolean directBilling;
    private boolean registeredByInternalUser; // Flag to check actor type

    // Legal Person fields
    private String corporateName; // Razão Social
    private String cnpj;

    // Natural Person fields
    private String name;
    private String cpf;
    
    // We will add Foreign Person fields here later

    // Getters and Setters for all fields...
    
    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean isDirectBilling() {
        return directBilling;
    }

    public void setDirectBilling(boolean directBilling) {
        this.directBilling = directBilling;
    }



    public boolean isRegisteredByInternalUser() {
        return registeredByInternalUser;
    }

    public void setRegisteredByInternalUser(boolean registeredByInternalUser) {
        this.registeredByInternalUser = registeredByInternalUser;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
