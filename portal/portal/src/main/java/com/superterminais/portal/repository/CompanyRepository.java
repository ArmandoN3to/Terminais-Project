package com.superterminais.portal.repository;

import com.superterminais.portal.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // A mágica acontece aqui!
    // O Spring Data JPA cria automaticamente os métodos como save(), findById(), findAll(), etc.
    // Você não precisa escrever nenhuma implementação.
}