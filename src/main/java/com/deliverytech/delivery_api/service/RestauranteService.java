package com.deliverytech.delivery_api.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;

@Service
public class RestauranteService {

    private final RestauranteRepository repository;

    public RestauranteService(RestauranteRepository repository) {
        this.repository = repository;
    }

    public Restaurante salvar(Restaurante restaurante) {

        if (restaurante.getNome() == null || restaurante.getNome().isEmpty()) {
            throw new RuntimeException("Nome do restaurante é obrigatório");
        }

        return repository.save(restaurante);
    }

    public List<Restaurante> listar() {
        return repository.findAll();
    }

    public List<Restaurante> buscarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    public void inativar(Long id) {
        Restaurante restaurante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        restaurante.setAtivo(false);
        repository.save(restaurante);
    }
}