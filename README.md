# Annotation

````markdown
# 📝 Bloco de Anotações - Spring Boot, MySQL e Docker

Este projeto é uma API REST desenvolvida com **Java + Spring Boot**, que oferece um sistema de **bloco de anotações com autenticação de usuários**. O sistema permite que usuários façam login e gerenciem suas próprias anotações de forma segura, com persistência no **MySQL** e infraestrutura containerizada com **Docker**.

---

## ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security (autenticação com senha criptografada - BCrypt)
- MySQL
- Docker & Docker Compose
- API RESTful
- Lombok (opcional)

---

## 📚 Funcionalidades

### 👤 Usuários
- ✅ Cadastrar novo usuário
- 🆔 Buscar usuário por ID
- 📄 Listar todos os usuários
- ✏️ Atualizar informações do usuário
- ❌ Deletar usuário

### 🗒️ Anotações (Notas)
- ➕ Criar nova anotação associada ao usuário
- 🧾 Buscar todas as anotações de um usuário
- ✏️ Atualizar anotação
- ❌ Deletar anotação

---

## 🔐 Segurança

- As senhas dos usuários são armazenadas com **criptografia BCrypt**
- A API valida os dados e controla o acesso por usuário (dependendo da configuração)
- (Opcional) Pode ser integrado com autenticação JWT futuramente

---

## 🐳 Como Executar com Docker

1. Clone o repositório:
   ```bash
   git clone https://github.com/maxsanttos/annotation.git
   cd bloco-anotacoes
````
2. Acesse a aplicação:

   ```
   http://localhost:8080
   ```

---

## 🛠️ Estrutura do Projeto

```bash
 Annotation/
├── src/
│   └── main/
│       ├── java/com/annotation/annotation/
│       │   ├── controller/           # Endpoints REST
│       │   ├── service/              # Regras de negócio
│       │   ├── repository/           # Interfaces do Spring Data JPA
│       │   └── model/entity/         # Entidades e DTOs
│       │       ├── User.java
│       │       ├── Note.java
│       │       ├── UserRole.java
│       │       ├── UserUpdateDTO.java
│       │       └── UserMapper.java
│       └── resources/
│           └── application.properties
├── Dockerfile                       # Configuração para container da aplicação
└── docker-compose.yml               # Orquestração com banco de dados
```

---


## 🧪 API Endpoints (examples)

| Method | Endpoint            | Description               |
|--------|---------------------|---------------------------|
| POST   | `/users`            | Create new user           |
| PUT    | `/users/{id}`       | Update existing user      |
| GET    | `/users`            | List all users            |
| GET    | `/users/{id}`       | Get user by ID            |
| DELETE | `/users/{id}`       | Delete user by ID         |
| GET    | `/auth/me`          | Get logged-in user info   |

## 🔧 Running Locally

```bash
# Clone the repository
git clone https://github.com/your-username/annotation-api.git
cd annotation-api

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run



## 📌 Observações

* É possível estender o projeto facilmente para usar autenticação com JWT
* Para fins de simplicidade, o projeto ainda não possui front-end, mas pode ser integrado com Angular, React, etc.
* Banco de dados criado automaticamente ao iniciar com Docker


## 👨‍💻 Autor

maxsanttos
