package com.deliverytech.delivery_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.deliverytech.delivery_api.entity.Produto;
import com.deliverytech.delivery_api.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // Criar produto para um restaurante
    @PostMapping("/{restauranteId}")
    public Produto salvar(@PathVariable Long restauranteId,
                          @RequestBody Produto produto) {
        return service.salvar(restauranteId, produto);
    }

    // Listar todos
    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    // Buscar produtos de um restaurante
    @GetMapping("/restaurante/{id}")
    public List<Produto> buscarPorRestaurante(@PathVariable Long id) {
        return service.buscarPorRestaurante(id);
    }
}