# Sertão API

Apis da Clínica Médica do Sertão.

## Requisitos

- Java 17
- Maven

## Configuração

A aplicação utiliza um arquivo `.env` na raiz do projeto para gerenciar variáveis de ambiente.
Crie um arquivo `.env` baseado no exemplo abaixo:

```properties
DB_URL=jdbc:mysql://localhost:3306/sertao_db
DB_USERNAME=root
DB_PASSWORD=secret
DB_DATABASE=sertao_db
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
