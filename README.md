# Delivery Tech API

API REST desenvolvida com Spring Boot para gerenciamento de clientes e pedidos, implementando autenticação JWT stateless, paginação e boas práticas de arquitetura.

## Tecnologias Utilizadas

- Java 17+

- Spring Boot

- Spring Web

- Spring Data JPA

- Spring Security

- JWT (JSON Web Token)

- H2 Database

- Swagger / OpenAPI

- Maven

- JUnit 5

- Mockito

## Arquitetura

O projeto segue separação em camadas:

delivery-api
│
├── src/main/java/com/deliverytech/delivery_api
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   ├── dto
│   ├── config
│   ├── security
│   └── exception
│
├── src/test
│
├── pom.xml
└── README.md

## Segurança

A API utiliza autenticação JWT stateless.

- Login gera token

- Token deve ser enviado no header

- Endpoints protegidos exigem autenticação

- Filtro JWT valida requisições

## Autenticação
Login

POST /auth/login

Request:

{
  "username": "admin",
  "password": "123"
}

Response:

{
  "token": "seu_token_aqui"
}


## Usando o Token

Enviar no header:

Authorization: Bearer seu_token_aqui

No Swagger:

1. Clique em Authorize

2. Cole: Bearer seu_token_aqui


## Documentação Swagger

Acesse:

http://localhost:8080/swagger-ui.html

ou

http://localhost:8080/swagger-ui/index.html


## Como Executar

./mvnw spring-boot:run

Ou:

mvn spring-boot:run


## Rodar Testes

./mvnw test

## Funcionalidades

### Cliente

- Cadastro de cliente

- Atualização de cliente

- Ativar / Desativar cliente

- Listagem paginada de clientes

### Pedido

Criação de pedido

Relacionamento Cliente → Pedido

### Segurança

- Autenticação JWT

- Proteção de endpoints

- Filtro customizado

- Configuração stateless

### Testes

- Testes unitários (Service)

- Testes de Controller com MockMvc

- Uso de Mockito

- Uso de @MockBean## 

# Desenvolvedor

[Anderson Almeida de Lemos] - [Fundação FAT - Arquiterura de Sistema - Turma Noite]
Desenvolvido com JDK 21 e Spring Boot 3.2.5
