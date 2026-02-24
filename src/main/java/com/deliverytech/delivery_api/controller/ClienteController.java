package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    /**
     * POST - Criar cliente
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO cadastrar(@Valid @RequestBody ClienteDTO dto) {
        return service.cadastrarCliente(dto);
    }

    /**
     * GET - Buscar por ID
     */
    @GetMapping("/{id}")
    public ClienteResponseDTO buscar(@PathVariable Long id) {
        return service.buscarClientePorId(id);
    }

    /**
     * GET - Listar ativos
     */
    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return service.listarClientesAtivos();
    }

    /**
     * PUT - Atualizar cliente
     */
    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO dto) {
        return service.atualizarCliente(id, dto);
    }

    /**
     * PATCH - Ativar/Desativar
     */
    @PatchMapping("/{id}/status")
    public void alterarStatus(@PathVariable Long id) {
        service.ativarDesativarCliente(id);
    }
}