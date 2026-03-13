# Clinica Médica do Sertão API

Api's da Clínica Médica do Sertão.

## Requisitos

- Java 17
- Maven

## Configuração

A aplicação utiliza um arquivo `.env` na raiz do projeto para gerenciar variáveis de ambiente.
Crie um arquivo `.env` baseado no exemplo abaixo:

```properties
DB_URL=jdbc:mysql://localhost:3306/clinica_sertao_db
DB_USERNAME=root
DB_PASSWORD=secret
DB_DATABASE=clinica_sertao_db
```

## Como executar com Docker

Para rodar a aplicação e o banco de dados via Docker, execute o seguinte comando na raiz do projeto:

```bash
docker-compose up --build
```

Para limpar os volumes e remover a base de dados atual.

```bash
docker compose down -v
```

## Como executar

Para rodar a aplicação, execute o seguinte comando na raiz do projeto:

```bash
mvn spring-boot:run
```

## Documentação da API (Swagger)

Após iniciar a aplicação, você pode acessar a documentação interativa (Swagger UI) através do seguinte endereço:

- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
