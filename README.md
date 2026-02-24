# deliverytech

## Delivery Tech API

API REST desenvolvida com Spring Boot para gerenciamento de clientes.

## Tecnologias Utilizadas

Java 21

Spring Boot

Spring Web

Spring Data JPA

H2 Database

ModelMapper

Maven

## Estrutura do Projeto
src/main/java/com/deliverytech/delivery_api
 ├── controller
 │    └── ClienteController.java
 ├── service
 │    └── ClienteService.java
 ├── repository
 │    └── ClienteRepository.java
 ├── entity
 │    └── Cliente.java
 ├── dto
 │    ├── ClienteDTO.java
 │    └── ClienteResponseDTO.java
 ├── exception
 │    └── ResourceNotFoundException.java
 └── DeliveryApiApplication.java

## Como executar o projeto
./mvnw clean install
./mvnw spring-boot:run

## Aplicação disponível em:

http://localhost:8080

## Endpoints disponíveis
🔹 Criar Cliente

POST

/api/clientes

Body:

{
  "nome": "Anderson",
  "email": "anderson@email.com"
}
🔹 Listar Clientes Ativos

GET

/api/clientes
🔹 Buscar Cliente por ID

GET

/api/clientes/{id}
🔹 Atualizar Cliente

PUT

/api/clientes/{id}
🔹 Ativar/Desativar Cliente

PATCH

/api/clientes/{id}/status


## Banco de Dados

Banco em memória H2.

Console disponível em:

http://localhost:8080/h2-console

JDBC URL:

jdbc:h2:mem:deliverydb

Usuário:

sa

Senha:

(vazio)

## 👨‍💻 Desenvolvedor
[Anderson Almeida de Lemos] - [Fundação FAT - Arquiterura de Sistema - Turma Noite]
Desenvolvido com JDK 21 e Spring Boot 4.0.2
