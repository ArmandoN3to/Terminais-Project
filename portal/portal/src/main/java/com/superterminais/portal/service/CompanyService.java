package com.superterminais.portal.service;

import com.superterminais.portal.model.Company;
import com.superterminais.portal.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired // 2. Injeta a dependência do repositório automaticamente
    private CompanyRepository empresaRepository;

    public Company criarEmpresa(Company newCompany) {
        // REGRA DE NEGÓCIO: Um usuário externo sempre cria um cadastro que precisa de aprovação.
        newCompany.setStatus("AGUARDANDO_APROVACAO"); // [cite: 24, 107]

        // 3. Salva a empresa no banco de dados usando o repositório
        return empresaRepository.save(newCompany);
    }
}
