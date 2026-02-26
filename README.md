# deliverytech

# Delivery Tech API

API REST desenvolvida com Spring Boot para gerenciamento de clientes e pedidos.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security (JWT)
- H2 Database
- Swagger / OpenAPI
- Maven
- JUnit 5
- Mockito

---

## Estrutura do Projeto


delivery-api/
│
├── src/main/java/com/deliverytech/delivery_api
│ ├── controller
│ ├── service
│ ├── repository
│ ├── model
│ ├── dto
│ ├── config
│ └── exception
│
├── src/test
├── pom.xml
└── README.md


---

## Segurança

A API utiliza autenticação via JWT.

### Login

POST `/auth/login`

```json
{
  "username": "admin",
  "password": "123"
}

Retorna:

{
  "token": "..."
}

Use o token no Swagger clicando em Authorize.

## Swagger

Acesse:

http://localhost:8080/swagger-ui.html

ou

http://localhost:8080/swagger-ui/index.html

## Como Executar
./mvnw spring-boot:run

## Rodar Testes
./mvnw test

## Funcionalidades

Cadastro de Cliente

Atualização de Cliente

Ativar/Desativar Cliente

Listagem de Clientes

Criação de Pedido

Relacionamento Cliente → Pedido

Segurança com JWT

Testes unitários e de controller

## Desenvolvedor
[Anderson Almeida de Lemos] - [Fundação FAT - Arquiterura de Sistema - Turma Noite]
Desenvolvido com JDK 21 e Spring Boot 4.0.2
