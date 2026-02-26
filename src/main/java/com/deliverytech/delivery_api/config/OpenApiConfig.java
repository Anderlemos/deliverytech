package com.deliverytech.delivery_api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe responsável por configurar os metadados da documentação Swagger.
 * Aqui definimos título, versão, descrição, contato e licença da API.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI deliveryTechOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Delivery Tech API")
                        .description("API REST para gerenciamento de clientes, restaurantes, produtos e pedidos.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Anderson Almeida")
                                .email("anderson@email.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório GitHub")
                        .url("https://github.com/seuusuario/delivery-api"));
    }
}