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
- **PostgreSQL** (ambiente de produção)
- **Apache Kafka**
- **Spring Cloud OpenFeign** (integração com API externa)
- **Lombok**
- **Springdoc OpenAPI** (documentação da API)

## Requisitos de Configuração

### Configuração do Kafka
Certifique-se de que o Kafka está em execução e configurado corretamente. Defina o servidor bootstrap no arquivo `application.yml`:

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    topic:
      resultado-votacao: resultado-votacao-topic
```

### Banco de Dados

#### Ambiente de Teste
Por padrão, o H2 Database é usado para testes:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

#### Ambiente de Produção
Configure o PostgreSQL no arquivo `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://<host>:<port>/<database>
    driver-class-name: org.postgresql.Driver
    username: <usuario>
    password: <senha>
  jpa:
    hibernate:
      ddl-auto: update
```

## Endpoints da API

### Criar uma Sessão
**POST** `/sessao`

**Request Body:**
```json
{
  "pautaId": 1,
  "duracao": 30
}
```

**Response:**
```json
{
  "id": 1,
  "pautaId": 1,
  "dataAbertura": "2025-01-22T08:00:00",
  "dataFechamento": "2025-01-22T08:30:00",
  "resultadoPublicado": false
}
```

### Registrar Voto
**POST** `/voto`

**Request Body:**
```json
{
  "pautaId": 1,
  "cpfAssociado": "12345678909",
  "voto": true
}
```

**Response:**
```json
{
  "id": 1,
  "pautaId": 1,
  "cpfAssociado": "12345678909",
  "voto": true
}
```

### Consultar Resultado da Votação
**GET** `/sessao/{id}/resultado`

**Response:**
```json
{
  "idSessao": 1,
  "tituloPauta": "Exemplo de Pauta",
  "totalVotos": 100,
  "votosSim": 60,
  "votosNao": 40,
  "resultado": "APROVADA"
}
```

## Testes Automatizados

### Testes Unitários
Os testes unitários cobrem:
- Regras de negócio, como validação de CPF e votos duplicados.
- Persistência de dados no banco.

Execute os testes usando:
```bash
mvn test
```

### Testes de Performance
O `VotacaoLoadTest` simula cenários com alto volume de votos. Configure o JMeter ou ferramentas semelhantes para executar testes de carga e validar a escalabilidade.

## Documentação da API
Acesse a documentação Swagger em:
```
http://localhost:8081/swagger-ui/index.html
```

## Estratégia de Versionamento
Adote o versionamento de API usando prefixos de URI, como `/v1/sessao` e `/v2/sessao`. Isso garante compatibilidade retroativa e facilita a evolução da API.

## Melhorias Futuras
- Implementar autenticação e autorização com OAuth2.
- Adicionar suporte a banco de dados NoSQL para armazenar logs e eventos.
- Integrar monitoramento com ferramentas como Prometheus e Grafana.

