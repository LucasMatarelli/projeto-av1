# Sistema de Gerenciamento de Funcionários e Departamentos

## 📝 Descrição do Projeto

Este é um projeto de aplicação monolítica desenvolvido com Spring Boot, conforme o enunciado da AV1. O sistema oferece uma API REST para um CRUD completo de Funcionários e Departamentos, além de uma interface web construída com Thymeleaf para a manipulação e visualização dos dados. A persistência de dados é feita com MariaDB.

## 🚀 Tecnologias Utilizadas

* **Backend:** Java 17, Spring Boot
* **Web/API:** Spring Web (MVC + REST)
* **Persistência:** Spring Data JPA, MariaDB, H2 Database
* **Frontend:** Thymeleaf
* **Outras:** Lombok, Spring DevTools

## ⚙️ Como Executar a Aplicação

Siga os passos abaixo para executar o projeto localmente:

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/LucasMatarelli/projeto-av1.git](https://github.com/LucasMatarelli/projeto-av1.git)
    cd projeto-av1
    ```

2.  **Configure o Banco de Dados:**
    * Certifique-se de ter o MariaDB instalado e rodando.
    * Crie um banco de dados chamado `empresa` com o comando: `CREATE DATABASE empresa;`

3.  **Configure as Credenciais:**
    * Abra o arquivo `src/main/resources/application.properties`.
    * Altere as seguintes linhas com seu usuário e senha do MariaDB:
        ```properties
        spring.datasource.username=seu_usuario_aqui
        spring.datasource.password=sua_senha_aqui
        ```

4.  **Execute a Aplicação:**
    * Abra o projeto em sua IDE (IntelliJ, VS Code, etc).
    * Encontre e execute a classe principal `SeuProjetoApplication.java`.

5.  **Acesse a Aplicação:**
    * Abra seu navegador e acesse: `http://localhost:8080`

## Endpoints da API REST

Você pode usar o Postman ou `curl` para testar os endpoints da API.

#### **Criar um novo Departamento**
* **Método:** `POST`
* **URL:** `/departamentos`
* **Body (JSON):**
    ```json
    {
        "nome": "Marketing",
        "localizacao": "Andar 2"
    }
    ```

#### **Listar todos os Departamentos**
* **Método:** `GET`
* **URL:** `/departamentos`

#### **Criar um novo Funcionário**
* **Método:** `POST`
* **URL:** `/funcionarios`
* **Body (JSON):**
    ```json
    {
        "nome": "Mariana Costa",
        "email": "mariana.costa@empresa.com",
        "dataAdmissao": "2025-10-02",
        "departamento": {
            "id": 1
        }
    }
    ```
