package com.deliverytech.delivery_api.dto;

import com.deliverytech.delivery_api.entity.Cliente;

public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private boolean ativo;

    // Construtor vazio (obrigatório para ModelMapper)
    public ClienteResponseDTO() {
    }

    // Construtor que recebe entidade (resolve seu erro)
    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.ativo = cliente.isAtivo();
    }

    // Construtor completo opcional
    public ClienteResponseDTO(Long id, String nome, String email, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}