package com.deliverytech.delivery_api;


import com.deliverytech.delivery_api.model.Usuario;
import com.deliverytech.delivery_api.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DeliveryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApiApplication.class, args);
    }

    @Bean
    @Profile("!test")
    CommandLineRunner init(UsuarioRepository usuarioRepository) {
        return args -> {
            System.out.println("Clientes iniciais carregados com sucesso!");
        };
    }
}