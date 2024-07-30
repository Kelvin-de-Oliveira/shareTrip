# ShareTrip API

### ShareTrip é uma plataforma de compartilhamento de relatos de viagem, oferecendo uma API para gerenciar destinos, relatos, usuários e interações, bem como autenticar usuários. Este repositório contém o código fonte da API construída em Java, utilizando Spring Boot e MongoDB Atlas como banco de dados. As requisições foram testadas no Postman, e a estratégia de versionamento do projeto foi implementada por meio do uso de branches.
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

    Destino: Criar, ler, atualizar e deletar destinos.
    Usuario: Criar, ler, atualizar e deletar usuários.
    Relato: Criar, ler, atualizar e deletar relatos.

### Requisições de Autenticação

    Autenticação de usuário para acessar a plataforma.

### Outras Operações

    Ver relatos de um usuário.
    Ver relatos relacionados a um destino.
    Curtir um relato.
    Comentar em um relato.

### Endpoints da API
Destino

    GET /destinos: Lista todos os destinos.
    GET /destinos/{id}: Recupera um destino pelo ID.
    POST /destinos: Cria um novo destino.
    PUT /destinos/{id}: Atualiza um destino existente.
    DELETE /destinos/{id}: Deleta um destino.

Usuario

    GET /usuarios: Lista todos os usuários.
    GET /usuarios/{id}: Recupera um usuário pelo ID.
    POST /usuarios: Cria um novo usuário.
    PUT /usuarios/{id}: Atualiza um usuário existente.
    DELETE /usuarios/{id}: Deleta um usuário.

Relato

    GET /relatos: Lista todos os relatos.
    GET /relatos/{id}: Recupera um relato pelo ID.
    POST /relatos: Cria um novo relato.
    PUT /relatos/{id}: Atualiza um relato existente.
    DELETE /relatos/{id}: Deleta um relato.

Autenticação

    POST /auth/login: Autentica um usuário.

Interação

    GET /relatos/usuario/{usuarioId}: Ver relatos de um usuário.
    GET /relatos/destino/{destinoId}: Ver relatos relacionados a um destino.
    POST /relatos/{relatoId}/curtir: Curtir um relato.
    POST /relatos/{relatoId}/comentar: Comentar em um relato.
