package com.deliverytech.delivery_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // Criar cliente
    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        return service.salvar(cliente);
    }

    // Listar clientes
    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    // Inativar cliente
    @PutMapping("/{id}/inativar")
    public void inativar(@PathVariable Long id) {
        service.inativar(id);
    }
}