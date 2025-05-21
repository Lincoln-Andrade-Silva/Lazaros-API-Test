# Lazaros-API-Test

API RESTful desenvolvida em Java com Spring Boot para gerenciamento de usuários e seus respectivos perfis de sistema.

## 🧩 Funcionalidades

- CRUD de Perfis de Sistema
  - Campos: `id`, `descricao`
  - Regras:
    - `descricao` é obrigatório
    - mínimo de 5 caracteres

- CRUD de Usuários
  - Campos: `id`, `nome`, `perfis` (lista de perfis)
  - Regras:
    - `nome` é obrigatório
    - mínimo de 10 caracteres
    - ao menos 1 perfil deve ser informado

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose

## 🐘 Banco de Dados (PostgreSQL)

A aplicação utiliza PostgreSQL como banco de dados, e já está pronta para rodar com Docker Compose.

### 🧪 Subindo o ambiente com Docker Compose

1. Certifique-se de que você tenha o Docker e o Docker Compose instalados.
2. Na raiz do projeto, execute:

```bash
docker-compose up -d
