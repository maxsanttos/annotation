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
   git clone https://github.com/seu-usuario/bloco-anotacoes.git
   cd bloco-anotacoes
````

2. Execute o projeto com Docker Compose:

   ```bash
   docker-compose up --build
   ```

3. Acesse a aplicação:

   ```
   http://localhost:8080
   ```

---

## 🛠️ Estrutura do Projeto

```bash
src/
├── main/
│   ├── java/com/seuusuario/blocoanotacoes/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── model/entity/
│   │       ├── User.java
│   │       └── Note.java
│   └── resources/
│       └── application.properties
docker-compose.yml
Dockerfile
```

---

## 🧪 Exemplos de Endpoints

### Usuários

* `POST /api/usuarios` → Criar usuário
* `GET /api/usuarios` → Listar todos
* `GET /api/usuarios/{id}` → Buscar por ID
* `PUT /api/usuarios/{id}` → Atualizar
* `DELETE /api/usuarios/{id}` → Deletar

### Notas

* `POST /api/usuarios/{id}/notas` → Criar nota para o usuário
* `GET /api/usuarios/{id}/notas` → Listar todas as notas do usuário
* `PUT /api/notas/{id}` → Atualizar nota
* `DELETE /api/notas/{id}` → Deletar nota

---

## 📌 Observações

* É possível estender o projeto facilmente para usar autenticação com JWT
* Para fins de simplicidade, o projeto ainda não possui front-end, mas pode ser integrado com Angular, React, etc.
* Banco de dados criado automaticamente ao iniciar com Docker

---

## 👨‍💻 Autor

maxsanttos

---

```



