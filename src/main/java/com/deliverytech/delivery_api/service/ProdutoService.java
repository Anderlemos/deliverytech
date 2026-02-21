package com.deliverytech.delivery_api.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.deliverytech.delivery_api.entity.Produto;
import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.repository.ProdutoRepository;
import com.deliverytech.delivery_api.repository.RestauranteRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Produto salvar(Long restauranteId, Produto produto) {

        if (produto.getPreco() == null || produto.getPreco() <= 0) {
            throw new RuntimeException("Preço deve ser maior que zero");
        }

        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        produto.setRestaurante(restaurante);

        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public List<Produto> buscarPorRestaurante(Long restauranteId) {
        return produtoRepository.findByRestauranteId(restauranteId);
    }
}