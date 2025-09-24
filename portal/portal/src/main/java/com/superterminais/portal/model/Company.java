package com.superterminais.portal.model;

import com.superterminais.portal.model.enums.CompanyStatus;
import com.superterminais.portal.model.enums.Profile;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "company_discriminator", discriminatorType = DiscriminatorType.STRING)
public abstract class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "trade_name") // Nome Fantasia
    private String tradeName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Profile profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyStatus status;

    @Column(name = "direct_billing", nullable = false)
    private boolean directBilling;

    @OneToMany(
        mappedBy = "company",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Attachment> attachments = new ArrayList<>();

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public boolean isDirectBilling() {
        return directBilling;
    }

    public void setDirectBilling(boolean directBilling) {
        this.directBilling = directBilling;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}