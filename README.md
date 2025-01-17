# Microsserviço de Envio de E-mail

## Tecnologias Utilizadas
[![My Skills](https://skillicons.dev/icons?i=java,spring,kafka,postgresql,docker)](https://skillicons.dev)


### Como Executar
- Informar o e-mail e a senha de app para utilizar o serviço de email
- Para executar o projeto, basta rodar o script `build.py`.

## Rotas

### Rota POST: Criar Usuário
`POST localhost:8080/api/user`

#### Exemplo de Payload:
```json
{
	"name" : "name",
	"email" : "example@gemail.com"
}
```
### Rota POST: Envio de email
 `POST localhost:8080/api/send`

 #### Exemplo de Payload:
```json
{
	"userId" : "userId",
	"emailTo" : "emailTo",
	"subject" : "subject",
	"text" : "text"
}
```