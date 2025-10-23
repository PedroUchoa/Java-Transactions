
# Java Transactions

Esse projeto foi desenvolvido para um desafio técnico visando apresentar habilidades de desenvolvedor backend e tem como escopo a criação de transações entre comerciantes e gerar estatisticas de transações em um certo periodo de tempo.


## Stack utilizada

**Back-end:** Java 17, Spring Boot 3.5.6, Lombok, lsf4, Spring doc, actuator, Junit, Mockito


## Rodando localmente

#### 1- Clonar o Repositório
- Abra seu terminal ou prompt de comando e clone o projeto com o seguinte comando:

```bash
  git clone https://github.com/PedroUchoa/Java-Transactions
```

#### 2- Configurar o Banco de Dados

- Abra o MySQL e crie um novo banco de dados.

- Acesse o arquivo src/main/resources/application.properties (ou application.yml) e atualize as configurações de conexão com suas credenciais do MySQL:

```
spring.datasource.url=jdbc:mysql://localhost/nome_do_seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

#### 3- Importar e Executar o Projeto na IDE

- Importe o projeto em sua IDE como um projeto Maven.

- Deixe que a IDE resolva e baixe todas as dependências do pom.xml.

- Vá para a classe principal da aplicação (geralmente localizada em src/main/java/) e execute-a fazendo o flyway criar as tabelas no banco de dados.

#### 4- Ver a documentação

- Acesse o caminho correto para acessar a documentação

```
http://localhost:8080/swagger-ui.html
```


## Documentação da API

#### Cria uma transação

```http
  POST /transaction
```

| Body   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `value` | `Double` | **Obrigatório**. Valor da Transação |
| `dateTime` | `DateTime` | **Obrigatório**. Horario que a transação é realizada |

#### Deleta todas as transações

```http
  DELETE /transaction
```

#### Retorna as estatisticas de transações

```http
  GET /statistics
```

| Body   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `interval` | `Integer` | **Opcional**. Intervalo de tempo que será utlizado para o retorno das estatisticas (Padrão 60 segundos) |


## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  mvn test
```

