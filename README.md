# Microserviço de Autenticação de Login - `shoppingcart`

## Visão Geral

- Objetivo: Este repositório contém o microserviço de carrinho de compras desenvolvido para uma aplicação de e-commerce.
  O serviço
  permite que clientes adicionem produtos ao carrinho, removam produtos do carrinho e finalizem a compra, gerenciando o
  estado do carrinho de compras de cada usuário.

- Arquitetura: O projeto foi desenvolvido em Java com Spring Boot, seguindo o padrão de arquitetura limpa. A aplicação
  é dividida em camadas de aplicação, domínio e infraestrutura.

- Segurança: A segurança da aplicação é garantida por meio de tokens JWT (JSON Web Token), que são gerados e validados

- Banco de Dados: Para facilitar o desenvolvimento e testes, a aplicação utiliza o banco de dados H2, que é um banco de
  dados em memória.

- Dependências: O projeto utiliza o Gradle como gerenciador de dependências e build.

- Endpoints: A API oferece endpoints para adicionar produtos ao carrinho, remover produtos do carrinho, finalizar a
  compra
  e consultar o carrinho de compras de um usuário.

- Execução: Para executar o projeto, basta clonar o repositório e executar o comando `./gradlew bootRun`.
- Nota: Necessário um token válido com role `USER` ou `ADMIN` para acessar os endpoints.

- Requisitos: Para executar o projeto, é necessário ter o JDK 17 ou superior e o Gradle 6.8 ou superior instalados na
  máquina.

- Exemplo de Requisição: Para facilitar a chamada das requisições, a collection da API pode ser encontrada no diretório
  `/collection`.

## Arquitetura

![arquitetura](./docs/images/desenho-solucao.drawio.png)

## Estrutura do Projeto

A estrutura do projeto está organizada em diferentes pacotes, cada um com responsabilidades específicas para manter o
código modular e fácil de manter.

| Pacote            | Descrição                | Responsabilidades                                                                                                          |
|-------------------|--------------------------|----------------------------------------------------------------------------------------------------------------------------|
| `/application`    | Camada de Aplicação      | Contém os serviços e controladores responsáveis pelo processamento das solicitações e pela lógica de negócio.              |
| `/domain`         | Camada de Domínio        | Define as entidades do domínio, repositórios e regras de negócio que regem o comportamento do sistema.                     |
| `/infrastructure` | Camada de Infraestrutura | Gerencia a comunicação com sistemas externos, como bancos de dados e serviços externos, e configurações de infraestrutura. |

## Segurança

A segurança do microserviço de autenticação é um aspecto crucial para garantir a proteção dos dados dos usuários e a
integridade do sistema. A seguir, são descritas as principais medidas de segurança implementadas:

### Uso de Tokens JWT e Roles

- **Tokens JWT (JSON Web Token)**: A autenticação e autorização na aplicação são gerenciadas por meio de tokens JWT.
  Esses tokens são gerados no momento do login e contêm informações codificadas sobre o usuário, como seu ID e suas
  roles (permissões). Cada token é assinado digitalmente para evitar falsificação.

- **Roles**: As roles são perfis de permissão atribuídos aos usuários. Na aplicação, existem três roles
  principais: `GUEST`, `USER` e `ADMIN`. Essas roles definem o nível de acesso e as ações que cada usuário pode realizar
  dentro da plataforma.

### Registro de Novos Usuários

Para evitar interceptação de requisições e a criação de usuários sem a devida permissão, foi tomada a decisão de deixar
como responsabilidade do backend definir as roles dos usuários. Com isso, foram criados endpoints específicos para o
cadastro de cada tipo de usuário:

- **Registro de Guest**: Aberto para todos, permite o cadastro de usuários com role `GUEST`.
- **Registro de User**: Aberto para todos, permite o cadastro de usuários com role `USER`.
- **Registro de Admin**: Restrito, permite o cadastro de usuários com role `ADMIN`. Para acessar este endpoint, é
  necessário um token válido com role `ADMIN`.

Essa abordagem garante que a definição de permissões seja controlada pelo backend, aumentando a segurança contra
tentativas de criação de usuários com permissões inadequadas.

## Endpoints

Os detalhes dos endpoints da API, incluindo descrições, parâmetros de entrada e exemplos de resposta, estão disponíveis
no arquivo [`API Endpoints Documentation`](./API_Endpoints_Documentation.md).


