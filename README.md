# Store API

## Descrição

A **Store API** é uma API simples, desenvolvida em **Java Spring Boot** para fins de estudo, que permite gerenciar produtos de uma loja, incluindo cadastro, atualização, venda e exclusão de produtos. A API utiliza **Spring Data JPA** para persistência em banco de dados PostgreSQL e está preparada para integração com documentação Swagger.

---

## Como Rodar

### 1. Clone o repositório

```bash
git clone https://github.com/JessicaMMattos/store-api-java.git
```

### 2. Configure o banco de dados

Crie um banco de dados PostgreSQL chamado `store` (ou outro nome que preferir).

### 3. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto com as informações do banco de dados:

```bash
DB_URL=
DB_USERNAME=
DB_PASSWORD=
```

### 4. Rode a aplicação

```bash
mvn spring-boot:run
```

A aplicação será iniciada na porta `8080` (configuração padrão).

---

## Endpoints

### Produtos

* **GET /products**
  - Lista todos os produtos.

* **GET /products/{id}**
  - Retorna um produto específico pelo `id`.

* **POST /products**
  - Cria um novo produto.
  > ⚠️ O `id` não precisa ser enviado (gerado automaticamente).

* **PUT /products/{id}**
  - Atualiza um produto existente pelo `id`.

* **DELETE /products/{id}**
  - Remove um produto pelo `id`.

* **POST /products/{id}/sell?quantity={quantidade}**
  - Realiza a venda de uma quantidade do produto e atualiza o estoque.

---

## Documentação Swagger

Após iniciar a aplicação, você pode acessar a documentação interativa da API em:

```
http://localhost:8080/swagger-ui.html
```

Lá é possível testar todos os endpoints diretamente no navegador.
