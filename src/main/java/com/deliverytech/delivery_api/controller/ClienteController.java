package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.api.ApiResponse;
import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operações relacionadas a clientes")
public class ClienteController {

    private final ClienteService service;

    // =========================
    // CADASTRAR CLIENTE
    // =========================
    @PostMapping
    @Operation(summary = "Cadastrar novo cliente")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> cadastrar(
            @RequestBody ClienteDTO dto) {

        ClienteResponseDTO response = service.cadastrarCliente(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> buscarPorId(
            @PathVariable Long id) {

        ClienteResponseDTO response = service.buscarClientePorId(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // =========================
    // LISTAR CLIENTES ATIVOS (COM PAGINAÇÃO)
    // =========================
    @GetMapping
    @Operation(summary = "Listar clientes ativos com paginação")
    public ResponseEntity<ApiResponse<Page<ClienteResponseDTO>>> listarClientes(
            @PageableDefault(size = 10) Pageable pageable) {

        Page<ClienteResponseDTO> page = service.listarClientesAtivos(pageable);
        return ResponseEntity.ok(ApiResponse.success(page));
    }

    // =========================
    // ATIVAR / DESATIVAR CLIENTE
    // =========================
    @PatchMapping("/{id}")
    @Operation(summary = "Ativar ou desativar cliente")
    public ResponseEntity<Void> ativarOuDesativar(@PathVariable Long id) {

        service.ativarDesativarCliente(id);

        return ResponseEntity.noContent().build();
    }

    // =========================
    // ATUALIZAR CLIENTE
    // =========================
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> atualizar(
            @PathVariable Long id,
            @RequestBody ClienteDTO dto) {

        ClienteResponseDTO response = service.atualizarCliente(id, dto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}