package com.superterminais.portal.controller;

import com.superterminais.portal.model.Company;
import com.superterminais.portal.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // 1. Define que esta classe é um controlador REST
@RequestMapping("/api/companies")
public class CompanyController {
    

    @Autowired
    private CompanyService companyService;

    // 3. Mapeia este método para requisições HTTP POST em "/api/companies"
    @PostMapping
    public ResponseEntity<Company> criar(@RequestBody Company company) {
        // @RequestBody converte o JSON vindo do frontend para um objeto Empresa

        // 4. Chama o serviço para aplicar a lógica de negócio e salvar
        Company empresaCriada = companyService.criarEmpresa(company);

        // 5. Retorna uma resposta HTTP 200 (OK) com a empresa criada no corpo
        return ResponseEntity.ok(empresaCriada);
    }
    
}
