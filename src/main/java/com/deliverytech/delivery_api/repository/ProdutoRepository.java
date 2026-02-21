package com.deliverytech.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.deliverytech.delivery_api.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByRestauranteId(Long restauranteId);

    List<Produto> findByCategoria(String categoria);

    List<Produto> findByDisponivelTrue();
}