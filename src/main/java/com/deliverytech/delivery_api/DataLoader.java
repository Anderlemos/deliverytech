package com.deliverytech.delivery_api;

import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    public DataLoader(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println("Inserindo clientes...");

        Cliente c1 = new Cliente("João Silva", "joao@email.com");
        Cliente c2 = new Cliente("Maria Souza", "maria@email.com");
        Cliente c3 = new Cliente("Carlos Lima", "carlos@email.com");

        clienteRepository.save(c1);
        clienteRepository.save(c2);
        clienteRepository.save(c3);

        System.out.println("Total de clientes: " + clienteRepository.count());

        System.out.println("Buscando por email...");
        clienteRepository.findByEmail("joao@email.com")
                .ifPresent(cliente ->
                        System.out.println("Encontrado: " + cliente.getNome())
                );

        System.out.println("Clientes ativos:");
        clienteRepository.findByAtivoTrue()
                .forEach(cliente ->
                        System.out.println(cliente.getNome())
                );
    }
}