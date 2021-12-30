## Desafio API - JAVA - API Admin e API Consumo
 

###  POSTMAN - ARQUIVO JSON PARA TESTES !!
  + [Está na raíz deste repositório (clique aqui)](APIS.postman_collection.json)
  + Esse arquivo está nos dois repositórios

###  Docs - swagger2
  + API Admin: https://localhost:8080/swagger-ui.html
  + API Consumo: https://localhost:9090/swagger-ui.html

### Aspectos funcionais

#### Portas
  + A API Admin roda na porta 8080 e a API Consumo na porta 9090

#### Login
  + Um usuário comum está registrado na base de dados da API Admin somente, com JWT. A API consumo apenas consome o endpoint de autenticação da API Admin, respondendo com o token.

#### Tratamento de dados
  + A API admin já trata os dados do DTO com o recurso @JsonView. Então os dados já chegam tratados na API consumo. Assim, se por algum motivo o usuário tiver acesso ao endpoint do admin, ainda assim verá os dados tratados, bem como na API consumo.
  + Mapstruct.

#### Foto
  + A foto está salva numa pasta do projeto. Os DTOs enviam o path da foto e o banco guarda o nome da foto.


###  DADOS DE LOGIN:
+ Para o admin:
  + Email: **admin**
  + Senha: **Gft2021**
+ Para um usuário (na api Consumo ou admin):
  + Email: **usuario**
  + Senha: **Gft2021**
  
###  BANCO
+ Ocorre com Flyway, então o banco é populado ao iniciar a aplicação com alguns usuários, políticos, partidos e todos os cargos.
+ No HEIDISQL, a porta do banco: **3306**; o user: **root**; a senha vazia.

###  POPULAÇÃO DO BANCO
+ Ocorre com Flyway, então o banco é populado ao iniciar a aplicação com alguns usuários, políticos, partidos e todos os cargos.


###  BANCO E IDE
+ MySQL + Flyway + HeidiSQL
+ Intellij Community
+ Java 11 -  module: Coretto 11
