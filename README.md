## Cashflow - Restful API

API RESTful básica para cadastro e gerenciamento de um fluxo de caixa.

### Estrutura do projeto

```bash
├── .azure
│   ├── 01-create-sqlserver-instance.sh
│   ├── 02-deploy-cashflow.sh
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
		groupId>org.springframework.boot</groupId>
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

---

### Deploy no Azure Web App via GitHub Actions
#### Scripts
Ordem de execução:
- 01-create-sqlserver-instance.sh;
- 02-deploy-cashflow.sh;
- 03-set-gh-secrets.sh;
- env-config.sh -> script de suporte

#### Faça o fork do projeto:
```
https://github.com/andremarko/ads-cp5-devops-4sem/fork
```

#### Clone seu projeto:
```bash
git clone https://github.com/[usuario-github]/[seu-repositorio-forkado]
cd [seu-repositorio-forkado]
```
#### Configure as variáveis do GitHub do projeto:
```bash
vim env-config.sh
```
```bash
GITHUB_REPO="[usuario-github]/[seu-repositorio-forkado]"
```
#### Dê permissão de execução para todos os scripts
```bash
cd .azure
chmod +x *.sh
```
#### Execute em ordem numérica (com exceção o env-config.sh, que não precisa ser executado)
#### Importante!:
- Verifique estar logado no Azure CLI antes de executar os scripts;
- Dê um fork deste projeto para executar o segundo script;
- Mude a variável GITHUB_REPO para o seu usuário e repositório (fork);
- Verifique ter baixado e autenticado o GH CLI antes de executar o terceiro script (configura as secrets no repositório;
- Por fim adicione ao .yaml criado no diretório `.github/workflows` as variáveis de ambiente (logo abaixo do run):
```yaml
run: mvn clean install
(logo abaixo)
env:
  JDBC_CONNECTION_STRING: ${{ secrets.JDBC_CONNECTION_STRING }}
  DB_ADMIN: ${{ secrets.DB_ADMIN }}
  DB_PASSWORD: ${{ secrets.DB_PASSWORD }}
```
#### Por fim, acesse o Swagger:

```
https://{$WEBAPP_NAME}.azurewebsites.net/swagger-ui/index
```

### Entidades do projeto - Modelo de dados

<img width="653" height="298" alt="image" src="https://github.com/user-attachments/assets/411e52fb-5f98-4309-8626-a74b3ba692d7" />

#### DDL das tabelas - ddl_cashflow.sql

```
cd scripts/
ls
ddl_cashflow.sql
```
---

### Endpoints
#### Caixa
##### 1️ Listar todos os fluxos de caixa
```bash
curl -X GET "http://[host]/api/caixa" \
     -H "Accept: application/json"
```

##### 2️ Buscar um fluxo de caixa por ID
```bash
curl -X GET "http://[host]/api/caixa/1" \
     -H "Accept: application/json"
```

##### 3️ Criar um fluxo de caixa
```
curl -X POST "http://[host]/api/caixa" \
     -H "Content-Type: application/json" \
     -d '{
           "tipoTransacao": "ENTRADA",
           "valor": 1500.50,
           "descricao": "Recebimento de cliente",
           "idCliente": 1
         }'
```
##### 4️ Atualizar um fluxo de caixa
```
curl -X PUT "http://[host]/api/caixa/1" \
     -H "Content-Type: application/json" \
     -d '{
           "tipoTransacao": "SAIDA",
           "valor": 500.00,
           "descricao": "Pagamento de fornecedor",
           "idCliente": 1
         }'
```
##### 5 Deletar um fluxo de caixa
```bash
curl -X DELETE "http://[host]/api/caixa/1"
```
---
**Cliente**
##### 1️ Listar todos os clientes
```bash
curl -X GET "http://[host]/api/cliente" \
     -H "Accept: application/json"
```
##### 2 Buscar um cliente por ID
```bash
curl -X GET "http://[host]/api/cliente/1" \
     -H "Accept: application/json"
```
##### 3 Criar um cliente
```bash
curl -X POST "http://[host]/api/cliente" \
     -H "Content-Type: application/json" \
     -d '{
           "nome": "João Silva",
           "cpf": "12345678901"
         }'
```
##### 4 Atualizar um cliente
```bash
curl -X PUT "http://[host]/api/cliente/1" \
     -H "Content-Type: application/json" \
     -d '{
           "nome": "João da Silva",
           "cpf": "12345678901"
         }'

```
##### 5 Deletar um cliente
```bash
curl -X DELETE "http://[host]/api/cliente/1"
```
