package com.deliverytech.delivery_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Aqui indicamos explicitamente qual é a classe principal
@SpringBootTest(classes = DeliveryApiApplication.class)
class DeliveryApiApplicationTests {

    @Test
    void contextLoads() {
        // Apenas verifica se o contexto Spring carrega
    }
}
