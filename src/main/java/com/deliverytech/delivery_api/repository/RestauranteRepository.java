package com.deliverytech.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.deliverytech.delivery_api.entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByCategoria(String categoria);

    List<Restaurante> findByAtivoTrueOrderByAvaliacaoDesc();
}