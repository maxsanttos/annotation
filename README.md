# 📘 Projeto API - Annotation com Spring Boot

Este projeto é uma API RESTful desenvolvida com Spring Boot para gerenciar **usuários e anotações (notes)**. Ele utiliza autenticação com Spring Security, persistência com JPA e documentação com Swagger (OpenAPI).

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL (ou H2 para testes)
- Swagger / OpenAPI (`springdoc-openapi`)
- JWT (caso esteja implementado)
- Maven

---

## 📁 Estrutura do Projeto

```
com.annotation.annotation
├── controller
│   ├── UserController
│   └── NoteController
├── dto
│   └── ...
├── model.entity
│   ├── User.java
│   └── Note.java
├── repository
│   ├── UserRepository.java
│   └── NoteRepository.java
├── security
│   └── SecurityConfig.java
└── service
    └── ...
```

---

## ⚙️ Configuração do Banco de Dados

No arquivo `application.properties` ou `application.yml`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sua_base
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🔐 Segurança

A API possui segurança baseada em autenticação. Endpoints protegidos requerem autenticação.

### ⚠️ Acesso ao Swagger liberado:
Os seguintes endpoints são públicos:

```
/swagger-ui.html  
/swagger-ui/**  
/v3/api-docs/**  
```

---

## 📄 Documentação da API

Acesse a interface do Swagger em:

```
🔗 http://localhost:8080/swagger-ui/index.html
```

Lá você verá todos os endpoints da API, métodos HTTP, parâmetros e exemplos de resposta.

---

## 🧪 Testando com Postman

1. Registre um usuário (`POST /users`)
2. Autentique-se, se aplicável
3. Acesse os endpoints protegidos com token (se houver)

---

## ✅ Endpoints Principais

### 🧍 Usuário

| Método | Rota             | Descrição                   |
|--------|------------------|-----------------------------|
| POST   | `/users`         | Cadastrar novo usuário      |
| GET    | `/users/{id}`    | Buscar usuário por ID       |

### 📝 Notas

| Método | Rota                 | Descrição                         |
|--------|----------------------|-----------------------------------|
| POST   | `/notes`             | Criar nota para usuário logado    |
| GET    | `/notes`             | Listar notas do usuário logado    |
| GET    | `/notes/{id}`        | Buscar nota por ID (autenticado)  |
| PUT    | `/notes/{id}`        | Atualizar nota                    |
| DELETE | `/notes/{id}`        | Deletar nota                      |

---

## 👤 Autor

**maxsanttos**

---

## 📜 Licença

Projeto livre para fins acadêmicos e estudos.
