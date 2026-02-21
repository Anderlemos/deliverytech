package com.deliverytech.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import com.deliverytech.delivery_api.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    List<Cliente> findByAtivoTrue();
}
