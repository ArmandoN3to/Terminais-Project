# Portal SuperTerminais - Backend

Este é o repositório do backend para a aplicação Portal SuperTerminais. A aplicação consiste em uma API RESTful construída com Java e Spring Boot, responsável por gerenciar o cadastro de empresas, autenticação de usuários e as regras de negócio associadas.

## ✨ Features

-   **Autenticação e Autorização via JWT:** Sistema de segurança robusto que diferencia usuários `INTERNOS` e `EXTERNOS`.
-   [cite_start]**Cadastro de Empresas:** Suporte para cadastro de Pessoas Jurídicas [cite: 12, 15, 16][cite_start], Pessoas Físicas [cite: 26, 28, 29] [cite_start]e Pessoas Estrangeiras[cite: 43, 45, 46].
-   [cite_start]**Upload de Documentos:** Endpoint para anexar documentos comprobatórios aos cadastros[cite: 18, 36, 53].
-   [cite_start]**Lógica de Negócio Baseada em Permissões:** O sistema aplica regras diferentes com base no tipo de usuário (ex: aprovação automática de cadastros para usuários internos [cite: 105]).
-   [cite_start]**Validações Brasileiras:** Validação matemática de CPF [cite: 81] [cite_start]e CNPJ[cite: 78].
-   [cite_start]**Endpoints Seguros:** Rotas de administração protegidas, acessíveis apenas por usuários autorizados[cite: 11].

## 🛠️ Tecnologias Utilizadas

-   **Java 17+**
-   **Spring Boot 3+**
-   **Spring Security 6+** (Autenticação e Autorização)
-   **Spring Data JPA** (Persistência de Dados)
-   **Hibernate** (ORM)
-   **SQLite** (Banco de Dados)
-   **Gradle** (Gerenciador de Build)
-   **JJWT (Java JWT)** (Geração e Validação de Tokens)
-   **Caelum Stella** (Validação de CPF/CNPJ)

## 🚀 Começando

Siga estas instruções para obter uma cópia do projeto e executá-lo em sua máquina local para desenvolvimento e testes.

### Pré-requisitos

-   JDK 17 ou superior.
-   Gradle 7+ (geralmente já vem com o projeto via Gradle Wrapper).
-   Uma IDE de sua preferência (IntelliJ IDEA, VS Code com extensões Java, etc.).

### Instalação e Execução

1.  **Clone o repositório:**
    ```sh
    git clone <url-do-seu-repositorio>
    ```

2.  **Navegue até a pasta do backend:**
    ```sh
    cd superterminais-portal/backend
    ```

3.  **Execute a aplicação:**
    Você pode rodar a aplicação diretamente pela sua IDE (encontrando e executando a classe `PortalApplication.java`) ou via linha de comando com o Gradle Wrapper:

    ```sh
    # No Windows
    .\gradlew bootRun

    # No Linux/macOS
    ./gradlew bootRun
    ```

    Na primeira execução, o arquivo do banco de dados (`database.db`) e a pasta de uploads (`uploads/`) serão criados automaticamente na raiz da pasta `backend`. A aplicação estará disponível em `http://localhost:8080`.

## 📖 Endpoints da API

A API segue os padrões RESTful. Todas as rotas (exceto `/api/auth/login`) exigem um token JWT no cabeçalho `Authorization: Bearer <token>`.

### Autenticação

| Método | Endpoint          | Proteção | Descrição                                  |
| :----- | :---------------- | :------- | :----------------------------------------- |
| `POST` | `/api/auth/login` | Pública  | Autentica um usuário e retorna um token JWT. |

**Exemplo de Request Body:**
```json
{
    "username": "user.externo",
    "password": "senha123"
}
