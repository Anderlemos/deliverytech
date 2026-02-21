package com.deliverytech.delivery_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.deliverytech.delivery_api.entity.Pedido;
import com.deliverytech.delivery_api.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    // Criar pedido para cliente
    @PostMapping("/{clienteId}")
    public Pedido criar(@PathVariable Long clienteId,
                        @RequestBody Pedido pedido) {
        return service.criar(clienteId, pedido);
    }

    // Listar todos
    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    // Atualizar status
    @PutMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable Long id,
                                  @RequestParam String status) {
        return service.atualizarStatus(id, status);
    }
}