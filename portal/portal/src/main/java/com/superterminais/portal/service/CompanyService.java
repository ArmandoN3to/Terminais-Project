package com.superterminais.portal.service;

import com.superterminais.portal.dto.CompanyRegistrationRequest;
import com.superterminais.portal.exception.RegistrationException;
import com.superterminais.portal.model.Company;
import com.superterminais.portal.model.LegalPerson;
import com.superterminais.portal.model.NaturalPerson;
import com.superterminais.portal.model.enums.CompanyStatus;
import com.superterminais.portal.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    // Constructor-based dependency injection is a best practice
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional // Ensures the whole method runs in a single database transaction
    public Company registerCompany(CompanyRegistrationRequest request) {
        validateRequest(request);

        Company company;
        switch (request.getCompanyType()) {
            case LEGAL_PERSON:
                company = createLegalPerson(request);
                break;
            case NATURAL_PERSON:
                company = createNaturalPerson(request);
                break;
            default:
                throw new RegistrationException("Unsupported company type.");
        }

        // Apply business rules for status based on user type
        if (request.isRegisteredByInternalUser()) {
            // [RN01] If the registration is done by an internal user, it should be auto-approved. 
            company.setStatus(CompanyStatus.APPROVED);
        } else {
            // [RN02] If the registration is done by an external user, it must await approval. 
            company.setStatus(CompanyStatus.PENDING_APPROVAL);
        }

        return companyRepository.save(company);
    }

    private LegalPerson createLegalPerson(CompanyRegistrationRequest request) {
        // [FE02][FE04] Check if CNPJ already exists
        companyRepository.findByCnpj(request.getCnpj()).ifPresent(c -> {
            throw new RegistrationException("CNPJ already registered.");
        });

        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setCorporateName(request.getCorporateName());
        legalPerson.setCnpj(request.getCnpj());
        populateCommonFields(legalPerson, request);
        return legalPerson;
    }

    private NaturalPerson createNaturalPerson(CompanyRegistrationRequest request) {
        // [FE03][FE05] Check if CPF already exists
        companyRepository.findByCpf(request.getCpf()).ifPresent(c -> {
            throw new RegistrationException("CPF already registered.");
        });

        NaturalPerson naturalPerson = new NaturalPerson();
        naturalPerson.setName(request.getName());
        naturalPerson.setCpf(request.getCpf());
        populateCommonFields(naturalPerson, request);
        return naturalPerson;
    }

    private void populateCommonFields(Company company, CompanyRegistrationRequest request) {
        company.setTradeName(request.getTradeName());
        company.setProfile(request.getProfile());
        company.setDirectBilling(request.isDirectBilling());
    }
    
    private void validateRequest(CompanyRegistrationRequest request) {
        if (request.getProfile() == null) {
            // [FE07] Field Profile cannot be empty [cite: 88]
            throw new RegistrationException("Profile is a mandatory field.");
        }
        // Other basic validations can be added here
    }
}