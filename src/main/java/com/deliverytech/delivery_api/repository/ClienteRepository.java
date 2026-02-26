package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findByAtivoTrue(Pageable pageable);

    boolean existsByEmail(String email);

}