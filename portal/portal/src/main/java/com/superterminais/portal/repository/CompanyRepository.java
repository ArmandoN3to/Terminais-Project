package com.superterminais.portal.repository;

import com.superterminais.portal.model.Company;
import com.superterminais.portal.model.LegalPerson;
import com.superterminais.portal.model.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    /**
     * Finds a LegalPerson entity by its unique CNPJ.
     * Spring Data JPA automatically creates the query from the method name.
     *
     * @param cnpj The CNPJ to search for.
     * @return an Optional containing the found LegalPerson or an empty Optional if not found.
     */
    Optional<LegalPerson> findByCnpj(String cnpj);

    /**
     * Finds a NaturalPerson entity by its unique CPF.
     *
     * @param cpf The CPF to search for.
     * @return an Optional containing the found NaturalPerson or an empty Optional if not found.
     */
    Optional<NaturalPerson> findByCpf(String cpf);

}