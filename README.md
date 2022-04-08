# Rest API de um blog

Api desenvolvida utilizando o [Ecossistema Spring](https://spring.io/) em conjunto com
[Jakarta Persistence JPA](https://jakarta.ee/specifications/persistence/),
banco de dados [Postgresql](https://www.postgresql.org/),
documentação com padrão [Swagger](https://swagger.io/),
autenticação com [JWT](https://jwt.io/),
mapeamento de objetos com [ModelMapper](http://modelmapper.org/)  e controle de versão do BD com [Flyway](https://flywaydb.org/).

Recursos da API (endpoints):

1 - Usuários - /users;<br>
2 - Postagens - /posts;<br>
3 - Imagens - /images;<br>
4 - Comentários - /comments;<br>
5 - Albuns - /albums.<br>

## Configuração de tokens

É possível configurar a duração em tempo dos tokens gerados, para isso é necessário acessar o arquivo
[application.properties](src/main/resources/application.properties) e alterar a propriedade:<br><br>
api.token.expiration-time<br><br>
OBS: A propriedade api.token.expirationtime será convertida em um atributo do tipo Duration,
o que significa que é necessário especificar após o valor, um sufixo de tempo que pode ser: ns, us, ms, s, m, h e d para nanossegundos, microssegundos, milissegundos,
segundos, minutos, horas e dias, respectivamente.<br><br>
A unidade padrão é milissegundos, o que significa que se não for especificado uma unidade
ao lado do valor numérico, o Spring converterá o valor em milissegundos, EX para expirar o token em 30 minutos: <br><br>

api.token.expiration-time:30m<br>

## Authenticação

Para consumir a API, primeiro é necessário logar no endpoint /users/login
passando um corpo contendo email e senha com o abaixo:<br>

  ```JSON
  {
  "email": "user@mail.com",
  "password": "password123456"
}
  ```

O retorno será algo como:<br>

  ```JSON
{
  "token": "TOKEN_GERADO",
  "expiresIn": "2022-04-08T19:31:06.2512037",
  "tokenProvider": "Bearer"
}
```
O token retornado no corpo da resposta deverá ser passado no cabeçalho "Authorization" precedido do sufixo "Bearer "
em todas as outras requisições<br>

## Usuário padrão

Para logar inicialmente na API, existe um usuário criado com o email "user@mail.com e senha "password12345".<br>

## Documentação

A documentação da API foi feita utilizando o padrão Swagger e pode ser acessada em /swagger-ui/index.html depois de subir a API.

