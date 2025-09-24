package com.superterminais.portal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id // 2. Define que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. Configura o ID para ser autoincrementado
    private Long id;

    private String personType; // "Física", "Jurídica", "Estrangeira"
    private String name;
    private String cpf;
    private String tradeName;
    private String status; // "AGUARDANDO_APROVACAO", "APROVADA", etc.

    // 4. JPA precisa de getters e setters para funcionar
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String tipoPessoa) {
        this.personType = tipoPessoa;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String nomeFantasia) {
        this.tradeName = nomeFantasia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

