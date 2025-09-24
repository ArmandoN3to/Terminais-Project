package com.superterminais.portal.dto.auth;

public class LoginResponse {
    private String token;
    // Construtor, Getters e Setters

    public LoginResponse(String token) { this.token = token; }
    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }
    
}