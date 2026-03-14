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

## 🚀 Como Testar a API com o Insomnia

Para facilitar o teste das funcionalidades da nossa API, disponibilizamos um arquivo de configuração para o **Insomnia** (um aplicativo que permite enviar comandos para a API de forma visual e simples).

### 1. Instalação

Se você ainda não tem o Insomnia instalado:

1. Acesse [insomnia.rest/download](https://insomnia.rest/download) e baixe a versão para o seu sistema operacional.
2. Instale e abra o aplicativo.

### 2. Importando as Configurações

Não é necessário configurar cada endereço manualmente. Siga os passos abaixo para importar nossa coleção pronta:

1. Com o Insomnia aberto, clique no botão **"Create"** (ou no ícone de "+" no topo da barra lateral).
2. Selecione a opção **"Import"**.
3. Escolha **"File"** e navegue até a pasta `insomnia` na raiz deste projeto.
4. Selecione o arquivo `sertao-api-insomnia.yaml` (ou o `.har`).
5. Confirme a importação. Agora você verá uma lista de pastas como `especialidades`, `medico` e `pacientes` no seu painel lateral.

### 3. Utilizando na Prática

1. Certifique-se de que a aplicação está rodando (veja a seção [Como executar com Docker](##como-executar-com-docker)).
2. No Insomnia, clique em uma das requisições (ex: `GET /medicos` para listar médicos).
3. Clique no botão azul **"Send"** (Enviar) no topo.
4. O resultado aparecerá no painel da direita.
   - **Status 200 ou 201:** Tudo certo!
   - **Status 400 ou 500:** Algo está faltando ou houve um erro no servidor.

> **Dica:** Nas pastas que dizem **POST** ou **PUT**, você verá uma aba chamada **Body** ou **JSON**. Ali você pode editar os nomes e dados que deseja enviar para o banco de dados antes de clicar em "Send".
