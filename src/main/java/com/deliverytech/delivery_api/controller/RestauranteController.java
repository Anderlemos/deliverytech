package com.deliverytech.delivery_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    public RestauranteController(RestauranteService service) {
        this.service = service;
    }

    // Criar restaurante
    @PostMapping
    public Restaurante salvar(@RequestBody Restaurante restaurante) {
        return service.salvar(restaurante);
    }

    // Listar todos
    @GetMapping
    public List<Restaurante> listar() {
        return service.listar();
    }

    // Buscar por categoria
    @GetMapping("/categoria/{categoria}")
    public List<Restaurante> buscarPorCategoria(@PathVariable String categoria) {
        return service.buscarPorCategoria(categoria);
    }

    // Inativar restaurante
    @PutMapping("/{id}/inativar")
    public void inativar(@PathVariable Long id) {
        service.inativar(id);
    }
}