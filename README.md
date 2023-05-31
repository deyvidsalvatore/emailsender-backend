# emailsender-backend

O "emailsender-backend" é um projeto de exemplo que demonstra como criar um serviço de envio de e-mails utilizando o Spring Boot.

## Configuração

Antes de executar o projeto, certifique-se de ter as seguintes dependências instaladas:

- Java Development Kit (JDK) 17 ou superior
- Apache Maven
- PostgreSQL
- Docker (Opcional)
## Executando o projeto

Para executar o projeto, siga as etapas abaixo:

1. Clone o repositório para o seu ambiente local:
```
git clone https://github.com/deyvidsalvatore/emailsender-backend.git
```
2. Navegue até o diretório raiz do projeto:
```
cd emailsender-backend
```
3. Execute o script com o postgres instalado e rodando:
```
psql -U postgres -d registration -f postgres.sql
```
4. Execute o seguinte comando para iniciar o aplicativo Spring Boot:
```
.\mvnw spring-boot:run
```
5. O aplicativo estará disponível em:
```
http://localhost:8080/
```


## Uso

O projeto oferece uma API REST para enviar e-mails. Aqui estão alguns exemplos de endpoints disponíveis:

- `POST /api/v1/registration`: Registra um novo usuário fornecendo as informações necessárias no corpo da solicitação.

- `GET /api/v1/registration/confirm?token={token}`: Confirma o registro do usuário com o token fornecido como parâmetro de consulta.

## Contribuição

Contribuições são bem-vindas! Se você tiver sugestões, melhorias ou correções, sinta-se à vontade para abrir um problema ou enviar uma solicitação pull.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

## Contato

Se você tiver alguma dúvida ou precisar de mais informações, entre em contato:

Deyvid Santos
deyvidsantosdatascience@gmail.com

---
Sinta-se à vontade para personalizar este README de acordo com as especificidades do seu projeto. Adicione seções adicionais ou informações relevantes para os usuários do projeto.
