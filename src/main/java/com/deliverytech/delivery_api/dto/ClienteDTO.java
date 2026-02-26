package com.deliverytech.delivery_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para cadastro e atualização de clientes.
 * 
 * DTO significa Data Transfer Object.
 * Ele é usado para transportar dados da requisição
 * sem expor diretamente a entidade do banco.
 */
@Schema(description = "Objeto utilizado para cadastro e atualização de clientes")
public class ClienteDTO {

    @Schema(
        description = "Nome completo do cliente",
        example = "João da Silva"
    )
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Schema(
        description = "Email do cliente",
        example = "joao@email.com"
    )
    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @Schema(
        description = "Telefone do cliente",
        example = "21999999999"
    )
    private String telefone;

    @Schema(
        description = "Endereço completo do cliente",
        example = "Rua A, 123"
    )
    private String endereco;

    // =============================
    // GETTERS E SETTERS
    // =============================

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}