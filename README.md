# Sessao Service

## Descrição
O **Sessao Service** é um microsserviço para gerenciar sessões de votação. Ele permite:

- Abrir e encerrar sessões de votação.
- Registrar votos para uma pauta.
- Calcular os resultados da votação.
- Publicar os resultados utilizando Apache Kafka.
- Validar CPFs de associados para garantir que estejam habilitados para votar.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Data JPA**
- **H2 Database** (ambiente de teste)
- **PostgreSQL** 
- **Apache Kafka**
- **Spring Cloud OpenFeign** (integração com API externa)
- **Lombok**
- **Springdoc OpenAPI** (documentação da API)

## Para rodar localmente utilizando o Docker 
 - Tenha instalado o Docker localmente
 - Clone a aplicação executando o seguinte comando git:
### https://github.com/newtonsj/teste_sicredi.git
 - Acesse o diretório sessao-service e execute o comando
### mvn clean package
 - No direório da aplicação, execute o comando
### docker compose up -d

## Documentação da API
Acesse a documentação Swagger em:
```
http://localhost:8081/swagger-ui/index.html
```

## Endpoints da API

### Pauta Controller
Gerencia as pautas do sistema.

GET /api/v1/pautas
Lista todas as pautas cadastradas.

POST /api/v1/pautas
Cria uma nova pauta.

### Sessão Controller
Gerencia as sessões de votação.

POST /api/v1/sessoes
Abre uma nova sessão de votação para uma pauta específica.

GET /api/v1/sessoes/{idSessao}/resultado
Retorna o resultado da sessão de votação (quantidade de votos, status, etc.).
### Votação Controller
Gerencia os votos do sistema.

POST /api/v1/votos
Registra um novo voto em uma determinada sessão.

```
## UI para visualização do Kafka
http://localhost:8080/

### Fluxo para iniciar uma votação:
 - POST /api/v1/pautas (Criar uma pauta)
 - POST /api/v1/sessoes (Criar uma sessão)
 - POST /api/v1/votos (Votar)

