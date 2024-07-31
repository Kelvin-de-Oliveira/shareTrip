# ShareTrip API

## ShareTrip é uma plataforma de compartilhamento de relatos de viagem, oferecendo uma API para gerenciar destinos, relatos, usuários e interações, bem como autenticar usuários. Este repositório contém o código fonte da API construída em Java, utilizando Spring Boot e MongoDB Atlas como banco de dados. As requisições foram testadas no Postman, e a estratégia de versionamento do projeto foi implementada por meio do uso de branches.

## Tecnologias Utilizadas

  -  Java 17
  -  Spring Boot 3.1.5
  -  MongoDB Atlas
  -  Postman (para testes de requisições)

## Estrutura do Projeto

### A API possui 5 classes principais:

   - Destino: Representa um destino de viagem.
   - Relato: Representa um relato de viagem compartilhado por um usuário.
   - Usuario: Representa um usuário da plataforma.
   - Interacao: Gerencia as interações dos usuários com os relatos (curtidas, comentários e visualizações).
   - Autenticação: Gerencia a autenticação dos usuários.

## Funcionalidades Principais
### Operações CRUD

   - Destino: Criar, ler, atualizar e deletar destinos.
   - Usuario: Criar, ler, atualizar e deletar usuários.
   - Relato: Criar, ler, atualizar e deletar relatos.

### Requisições de Autenticação

   - Autenticação de usuário para acessar a plataforma.

### Outras Operações

   - Seguir um usuário.
   - Ver relatos de um usuário.
   - Ver relatos relacionados a um destino.
   - Curtir um relato.
   - Comentar em um relato.
   - Ver comentarios de um relato
   - Avaliar um relato.
   - Avaliar um Destino.

### Endpoints da API
#### Destino

    GET /api/destino/all: Lista todos os destinos.
    GET /api/destino/{destinoId}: Recupera um destino pelo ID.
    POST /api/destino/add Cria um novo destino.
    PUT /api/destino/update/{destinoId}: Atualiza um destino existente.
    DELETE /api/destino/delete/{destinoId}: Deleta um destino.

#### Usuario

    GET /api/usuario/all: Lista todos os usuários.
    GET /api/usuario/{id}: Recupera um usuário pelo ID.
    POST /api/usuario/add:: Cria um novo usuário.
    PUT /api/usuario/update/{id}: Atualiza um usuário existente.
    DELETE /api/usuario/delete/{id}: Deleta um usuário.
    POST /api/usuario/authenticar: Autentica o acesso de um usuário.
    POST /api/{idSeguidor}/seguir/{idSeguido}.

#### Relato

    GET /api/relato/all: Lista todos os relatos.
    GET /api/relato/{id}: Recupera um relato pelo ID.
    POST /api/relato/add: Cria um novo relato.
    PUT /api/relato/update/{id}: Atualiza um relato existente.
    DELETE /api/relato/delete/: Deleta um relato.

#### Interação

    GET /api/usuario/interacao/view-usuario/{usuarioID}: Ver relatos de um usuário.
    GET /api/usuario/interacao/view-destino/{destinoId: Ver relatos relacionados a um destino.
    POST /api/publicacao/curtir/: Curtir um relato.
    POST api/relato/{relatoId}/comentar: Comentar em um relato.
