package com.superterminais.portal.controller;

import com.superterminais.portal.dto.CompanyRegistrationRequest;
import com.superterminais.portal.model.Company;
import com.superterminais.portal.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> registerCompany(@RequestBody CompanyRegistrationRequest request) {
        Company newCompany = companyService.registerCompany(request);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }
}