## Cashflow - Restful API

API RESTful básica para cadastro e gerenciamento de um fluxo de caixa.

### Estrutura do projeto

```bash
├── .azure
│   ├── 01-create-sqlserver-instance.sh
│   ├── 02-deploy-mottu-mapping.sh
│   ├── 03-set-gh-secrets.sh
│   └── env-config.sh
├── scripts
│   └── ddl_cashflow.sql
└── src
    ├── main
    │   ├── java
    │   │   └── org
    │   │       └── cashflow
    │   │           └── api
    │   │               ├── CashflowApiRestfulApplication.java
    │   │               ├── config
    │   │               │   ├── SwaggerConfig.java
    │   │               │   └── WebConfig.java
    │   │               ├── controller
    │   │               │   ├── CaixaController.java
    │   │               │   └── ClienteController.java
    │   │               ├── dto
    │   │               │   ├── CaixaRequestDTO.java
    │   │               │   ├── CaixaResponseDTO.java
    │   │               │   ├── ClienteRequestDTO.java
    │   │               │   └── ClienteResponseDTO.java
    │   │               ├── exception
    │   │               │   ├── CaixaNotFoundException.java
    │   │               │   ├── ClienteNotFoundException.java
    │   │               │   └── GlobalExceptionHandler.java
    │   │               ├── mapper
    │   │               │   ├── CaixaMapper.java
    │   │               │   └── ClienteMapper.java
    │   │               ├── model
    │   │               │   ├── Caixa.java
    │   │               │   ├── Cliente.java
    │   │               │   └── util
    │   │               │       └── TipoTransacao.java
    │   │               ├── repository
    │   │               │   ├── CaixaRepository.java
    │   │               │   └── ClienteRepository.java
    │   │               └── service
    │   │                   ├── CaixaService.java
    │   │                   └── ClienteService.java
    │   └── resources
    │       └── application.properties
```
### Dependencies (Maven)

```xml
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.microsoft.sqlserver</groupId>
			<artifactId>mssql-jdbc</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct</artifactId>
			<version>1.5.5.Final</version>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.8.8</version>
		</dependency>
		<dependency>
			<groupId>io.swagger.core.v3</groupId>
			<artifactId>swagger-annotations</artifactId>
			<version>2.2.32</version>
		</dependency>
```

### Entidades do projeto - Modelo dos dados

<img width="653" height="298" alt="image" src="https://github.com/user-attachments/assets/411e52fb-5f98-4309-8626-a74b3ba692d7" />

### Endpoints
**Caixa**
#### 1️ Listar todos os fluxos de caixa
```bash
curl -X GET "http://localhost:8080/api/caixa" \
     -H "Accept: application/json"
```

#### 2️ Buscar um fluxo de caixa por ID
```bash
curl -X GET "http://localhost:8080/api/caixa/1" \
     -H "Accept: application/json"
```

#### 3️ Criar um fluxo de caixa
```
curl -X POST "http://localhost:8080/api/caixa" \
     -H "Content-Type: application/json" \
     -d '{
           "tipoTransacao": "ENTRADA",
           "valor": 1500.50,
           "descricao": "Recebimento de cliente",
           "idCliente": 1
         }'
```
#### 4️ Atualizar um fluxo de caixa
```
curl -X PUT "http://localhost:8080/api/caixa/1" \
     -H "Content-Type: application/json" \
     -d '{
           "tipoTransacao": "SAIDA",
           "valor": 500.00,
           "descricao": "Pagamento de fornecedor",
           "idCliente": 1
         }'
```
#### 5 Deletar um fluxo de caixa
```bash
curl -X DELETE "http://localhost:8080/api/caixa/1"
```
---
**Cliente**
#### 1️ Listar todos os clientes
```bash
curl -X GET "http://localhost:8080/api/cliente" \
     -H "Accept: application/json"
```
#### 2 Buscar um cliente por ID
```bash
curl -X GET "http://localhost:8080/api/cliente/1" \
     -H "Accept: application/json"
```
#### 3 Criar um cliente
```bash
curl -X POST "http://localhost:8080/api/cliente" \
     -H "Content-Type: application/json" \
     -d '{
           "nome": "João Silva",
           "cpf": "12345678901"
         }'
```
#### 4 Atualizar um cliente
```bash
curl -X PUT "http://localhost:8080/api/cliente/1" \
     -H "Content-Type: application/json" \
     -d '{
           "nome": "João da Silva",
           "cpf": "12345678901"
         }'

```
#### 5 Deletar um cliente
```bash
curl -X DELETE "http://localhost:8080/api/cliente/1"
```

### Deploy no Azure Web App via GitHub Actions

#### Scripts
