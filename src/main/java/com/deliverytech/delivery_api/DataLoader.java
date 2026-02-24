package com.deliverytech.delivery_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository repository;

    @Override
    public void run(String... args) {

        // Evita duplicar dados se já existirem registros
        if (repository.count() > 0) {
            return;
        }

        Cliente c1 = new Cliente();
        c1.setNome("João");
        c1.setEmail("joao@email.com");
        c1.setTelefone("11999999999");
        c1.setEndereco("Rua A");
        c1.setAtivo(true);

        Cliente c2 = new Cliente();
        c2.setNome("Maria");
        c2.setEmail("maria@email.com");
        c2.setTelefone("11988888888");
        c2.setEndereco("Rua B");
        c2.setAtivo(true);

        Cliente c3 = new Cliente();
        c3.setNome("Carlos");
        c3.setEmail("carlos@email.com");
        c3.setTelefone("11977777777");
        c3.setEndereco("Rua C");
        c3.setAtivo(true);

        repository.save(c1);
        repository.save(c2);
        repository.save(c3);

        System.out.println("Clientes iniciais carregados com sucesso!");
    }
}