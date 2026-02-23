package com.deliverytech.delivery_api.entity;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private boolean ativo = true;

    // ===== CONSTRUTORES =====

    // Construtor vazio (OBRIGATÓRIO para o JPA)
    public Cliente() {
    }

    // Construtor com nome e email
    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.ativo = true;
    }

    // ===== GETTERS E SETTERS =====

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
