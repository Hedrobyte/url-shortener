## URL Shortener

Este projeto implementa um serviço de encurtamento de URLs longas, tornando-as mais compactas e fáceis de compartilhar.

## Índice

- [Funcionalidades](#funcionalidades)
- [Utilização da API](#utilização-da-api)
- [Passos para Executar](#executando-o-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Funcionalidades

- Recebe uma URL longa e retorna uma versão encurtada.
- O encurtamento consiste em um mínimo de 5 e um máximo de 10 caracteres alfanuméricos.
- As URLs encurtadas são salvas no banco de dados com um prazo de validade.
- Ao acessar a URL encurtada, ocorre o redirecionamento para a URL original.
- Retorna o código de status HTTP 404 caso a URL não seja encontrada no banco de dados.

## Utilização da API

### Encurtar uma URL

Endpoint: POST /shorten-url


- Request Body:
~~~json
{
    "url": "https://www.exemplo.com.br/muito-longa-e-detalhada-url"
}

~~~

- Response Body:
~~~json
{
    "url": "http://localhost:8080/DXB6V"
}
~~~

### Redirecionar para a URL Original

Endpoint: GET /{id}

- Ao acessar a URL encurtada `http://localhost:8080/DXB6V`, o serviço redirecionará para a URL original salva no banco de dados.

## Executando o Projeto

### Pré-requisitos

- Docker
- Docker Compose

### Passos para Executar

1. **Clone o Repositório**
~~~
git clone https://github.com/usuario/urlshortener.git
cd urlshortener
~~~

2. **Inicie os Serviços com Docker Compose**
~~~
docker compose up --build
~~~

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.1**
- **MongoDB**
- **Docker**
- **Apache Commons Lang**

## Estrutura do Projeto

- **Controller**: Contém os endpoints da API.
- **Service**: Contém a lógica de negócios para encurtamento e redirecionamento de URLs.
- **Repository**: Interface de comunicação com o MongoDB.
- **Entities**: Representa as entidades armazenadas no banco de dados.
- **DTOs**: Objetos de transferência de dados utilizados para comunicação entre as camadas da aplicação.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
