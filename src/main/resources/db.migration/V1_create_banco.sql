-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS annotation CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE annotation;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('USER', 'ADMIN'))
);


CREATE TABLE notes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user_note FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


INSERT INTO users (name, password, role) VALUES
('admin', '$2a$10$7QZiIL/nm7HyO0MviScu7ehhXJtb2u3KN4DU8X7RWvncdbAzR1SLK', 'ADMIN'), -- senha: admin123
('maxsuel', '$2a$10$CEe6AfAfJhKRzRDNHvuYmeXemVABPK3EXFMpyilX9muVJ7Wfocd.K', 'USER');  -- senha: 123456


INSERT INTO notes (title, content, user_id) VALUES
('Primeira Anotação', 'Esta é a primeira anotação do admin.', 1),
('Tarefa de Casa', 'Estudar Spring Security e revisar Docker.', 2),
('Ideia de Projeto', 'Criar um app de gerenciamento de tempo.', 2);



-- Verificar usuários
SELECT * FROM users;

-- Verificar anotações
SELECT * FROM notes;


