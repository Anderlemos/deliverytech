# deliverytech

# Delivery Tech API
Sistema de delivery desenvolvido com Spring Boot e Java 21.
API REST desenvolvida com Spring Boot para gerenciamento de clientes em um sistema de delivery.
Projeto criado para fins de estudo de arquitetura MVC, boas práticas de desenvolvimento backend e persistência com JPA.

## 🚀 Tecnologias
- **Java 21 LTS** (versão mais recente)
- Spring Boot 4.0.2
- Spring Data JPA
- H2 Database
- Maven
- REST API
- Arquitetura MVC

## 📂 Estrutura do Projeto
src
└── main
├── java/com/deliverytech/delivery_api
│ ├── controller
│ ├── service
│ ├── repository
│ └── entity
└── resources

## 🧱 Arquitetura

O projeto segue o padrão arquitetural **MVC (Model-View-Controller)**:

- **Entity (Model)** → Representação da entidade Cliente
- **Repository** → Comunicação com o banco de dados
- **Service** → Regras de negócio
- **Controller** → Endpoints REST

## 🗄️ Banco de Dados

O projeto utiliza o banco **H2 em memória**: 
spring.datasource.url=jdbc:h2:mem:deliverydb
Obs.: os dados são apagados ao encerrar a aplicação.

## ⚡ Recursos Modernos Utilizados
- Records (Java 14+)
- Text Blocks (Java 15+)
- Pattern Matching (Java 17+)
- Virtual Threads (Java 21)

## 🏃‍♂️ Como executar
1. **Pré-requisitos:** JDK 21 instalado
2. Clone o repositório:
git clone https://github.com/Anderlemos/deliverytech.git
3.Acessar a pasta:
Acessar a pasta
4. Execute: `./mvnw spring-boot:run`
5. Acesse: http://localhost:8080/health

## 📋 Endpoints
- GET /health - Status da aplicação (inclui versão Java)
- GET /info - Informações da aplicação
- GET /h2-console - Console do banco H2
- GET /Buscar cliente por ID
http://localhost:8080/clientes/{id}
- POST /clientes
- POST /Criar cliente
http://localhost:8080/clientes
- GET /clientes
- GET /restaurantes
- GET /produtos
- POST /pedidos

## Exemplo de Criar cliente

Body JSON:
''''json
{
  "nome": "Carlos",
  "email": "carlos@email.com"
} 
'''

## Deletar cliente
DELETE
http://localhost:8080/clientes/{id}

## Exemplo de Pedido
{
  "cliente": { "id": 1 },
  "valorTotal": 59.90,
  "status": "CRIADO"
}

## 🔧 Configuração
- Porta: 8080
- Banco: H2 em memória
- Profile: development

## Objetivo do Projeto
- Praticar arquitetura em camadas.
- Aplicar princípios do SOLID.
- Trabalhar com JPA e persistência.
- Criar API REST estruturada.
- Versionamento com Git e GitHub.

## 👨‍💻 Desenvolvedor
[Anderson Almeida de Lemos] - [Arquiterura de Sistema - Turma Noite]
Desenvolvido com JDK 21 e Spring Boot 4.0.2
