package com.deliverytech.delivery_api.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    // Salvar cliente
    public Cliente salvar(Cliente cliente) {

        // REGRA DE NEGÓCIO: email não pode ser duplicado
        if (repository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }

        return repository.save(cliente);
    }

    // Listar todos
    public List<Cliente> listar() {
        return repository.findAll();
    }

    // Buscar por ID
    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    // Inativar cliente
    public void inativar(Long id) {
        Cliente cliente = buscarPorId(id);
        cliente.setAtivo(false);
        repository.save(cliente);
    }
}