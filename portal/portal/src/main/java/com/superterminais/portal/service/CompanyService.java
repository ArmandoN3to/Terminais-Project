package com.superterminais.portal.service;

import com.superterminais.portal.dto.CompanyRegistrationRequest;
import com.superterminais.portal.exception.RegistrationException;
import com.superterminais.portal.model.Company;
import com.superterminais.portal.model.ForeignPerson;
import com.superterminais.portal.model.LegalPerson;
import com.superterminais.portal.model.NaturalPerson;
import com.superterminais.portal.model.enums.CompanyStatus;
import com.superterminais.portal.repository.CompanyRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.Authentication;

// import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

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
            case FOREIGN_PERSON:
                company = createForeignPerson(request);
                break;
            default:
                throw new RegistrationException("Unsupported company type.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isInternalUser = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_INTERNAL"));

            // Apply business rules for status based on user type
         if (isInternalUser) {
            company.setStatus(CompanyStatus.APPROVED);
        } else {
            company.setStatus(CompanyStatus.PENDING_APPROVAL);
        }

        return companyRepository.save(company);
    }

     private ForeignPerson createForeignPerson(CompanyRegistrationRequest request) {
        // Validar se o Identificador Estrangeiro já existe
        companyRepository.findByForeignId(request.getForeignId()).ifPresent(c -> {
            throw new RegistrationException("Foreign ID already registered.");
        });

        ForeignPerson foreignPerson = new ForeignPerson();
        foreignPerson.setCorporateName(request.getCorporateName());
        foreignPerson.setForeignId(request.getForeignId());
        populateCommonFields(foreignPerson, request);
        return foreignPerson;
    }

    private LegalPerson createLegalPerson(CompanyRegistrationRequest request) {

        // try {
        //     CNPJValidator validator = new CNPJValidator();
        //     validator.assertValid(request.getCnpj());
        // } catch (InvalidStateException e) {
        //     throw new RegistrationException("invalid CNPJ ");
        // }
        
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

        try {
            CPFValidator validator = new CPFValidator();
            validator.assertValid(request.getCpf());
        } catch (InvalidStateException e) {
            throw new RegistrationException("Invalid CPF ");
        }
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