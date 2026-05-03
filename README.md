# API de Gerenciamento de Biblioteca 📚

## Descrição

API REST desenvolvida com **Spring Boot** para gerenciamento de livros, autores e categorias.
O projeto permite operações completas de CRUD, além de consultas personalizadas como busca de livros por autor.

---

## Tecnologias utilizadas

* Java
* Spring Boot
* Spring Data JPA
* Lombok
* Hibernate
* Banco de dados H2
* Maven
* Postman para testes manuais

---

## ⚙️ Como executar o projeto

1. Clone o repositório
2. Abra em uma IDE (IntelliJ, Eclipse, etc.)
3. Execute a aplicação
4. Acesse:

```bash
http://localhost:8080
```

---

## Banco de dados

O projeto utiliza o banco **H2 em memória**.

* O banco é recriado a cada execução (`ddl-auto=create`)
* Possui carga inicial de dados (autor, categoria e livro)

Console do H2:

```bash
http://localhost:8080/h2-console
```

---

## Funcionalidades

* CRUD de autores
* CRUD de categorias
* CRUD de livros

---

## Diferenciais Técnicos

* Tratamento Global de Exceções: Garantindo que erros como buscas por IDs ou nomes inexistentes retornem `404 Not Found`.
* Códigos de Status HTTP Semânticos: Uso de `201 Created` para cadastros, `204 No Content` para deleções e `409 Conflict` para violações de integridade no banco.
* Segurança Transacional: Uso de `@Transactional` em operações críticas de deleção para garantir a consistência dos dados.
* Busca Customizada: Endpoint para localização de livros por autor ignorando maiúsculas/minúsculas (`Ignore Case`).

---

## Endpoints

### 📚 Livro

GET /livro - Lista todos os livros.

GET /livro/{id} - Busca um livro por ID.

GET /livro/autor?nome=Clarice Lispector - Busca livros pelo nome do autor (Ignore Case).

GET /livro/titulo?titulo=a hora da estrela - Busca livros pelo titulo (Ignore Case).

POST /livro - Cadastro de novo livro.

PUT /livro/{id} - Atualização parcial/total de um livro.

DELETE /livro/{id} - Remoção de um livro.


### 🖋️ Autores

GET /autor - Lista todos os autores.

GET /autor/{id} - Busca autor por ID.

GET /autor/nome?nome=Machado - Busca autor por nome.

POST /autor - Cadastro de novo autor.

PUT /autor/{id} - Atualização de autor.

DELETE /autor/{id} - Remoção de autor.


### 📁 Categorias

GET /categoria - Lista todas as categorias.

GET /categoria/{id} - Busca categoria por ID.

GET /categoria/nome?nome=Ficcao - Busca categoria por nome.

POST /categoria - Cadastro de nova categoria.

PUT /categoria/{id} - Atualização de categoria.

DELETE /categoria/{id} - Remoção de categoria.

---

## Exemplos de requisições

### ➕ Criar autor

```json
{
  "nome": "J. K. Rowling",
  "nacionalidade": "Britânica",
  "dataNascimento": "1965-07-31"
}
```

---

### ➕ Criar categoria

```json
{
  "nome": "Fantasia",
  "descricao": "Livros do gênero fantasia"
}
```

---

### ➕ Criar livro

```json
{
  "titulo": "Harry Potter e a Pedra Filosofal",
  "anoPublicacao": 1997,
  "descricao": "O primeiro dos sete livros da série de fantasia Harry Potter",
  "capa": "https://rocco.com.br/wp-content/uploads/2024/04/9786555324013.jpg",
  "autor": {"id" : 1},
  "categoria": {"id" : 1}
}
```

---

## Estrutura do projeto

* **Controller** → Requisições HTTP
* **Service** → Regras de negócio
* **Infraestrucure** → repository (acesso ao banco), entitys (representação das tabelas) e exception (exception handler)

---

## Plano de Testes e Validação (Postman)

Para garantir a qualidade das rotas e a integridade das regras de negócio, utilizei uma sequência de testes. O arquivo da coleção está disponível na pasta `/postman` deste repositório para facilitar a revisão.

### Ordem de Execução Realizada

Para cada entidade (**Autor**, **Categoria** e **Livro**), segui este fluxo lógico para validar o ciclo de vida completo dos dados e o comportamento do sistema sob erro:

1.  **GET (Busca Inicial)**: 
    *   Listagem geral para verificar os dados pré-carregados pelo `data.sql`.
2.  **POST (Criação)**: 
    *   **Sucesso**: Cadastro de um novo registro para confirmar o retorno `201 Created`.
    *   **Erro de Validação**: Envio de campos vazios para validar se o `@Valid` e o `GlobalExceptionHandler` retornam a mensagem limpa com `400 Bad Request`.
3.  **GET (Busca por ID)**: 
    *   Consulta do registro criado.
4.  **PUT (Atualização)**: 
    *   Alteração de características das entidades para validar se o banco de dados sobrescreve as informações corretamente.
5.  **GET (Busca de Verificação)**: 
    *   Nova consulta para garantir que o estado da entidade foi atualizado.
6.  **DELETE (Remoção e Integridade)**:
    *   **Teste de Conflito**: Tentativa de deletar um registro com vínculos ativos (categoria ou autor com livros) para validar o erro `409 Conflict`.
    *   **Teste de Sucesso**: Exclusão do registro criado para confirmar o retorno `204 No Content`.
7.  **GET (Confirmação Final)**: 
    *   Busca pelo ID removido para garantir que o sistema agora retorna `404 Not Found`.

---

## Como utilizar a Coleção Postman

Eu disponibilizei o arquivo JSON para que seja possível reproduzir esses mesmos testes:

1.  Localize o arquivo `Biblioteca Api.postman_collection.json` na pasta **`/postman`** deste repositório.
2.  No **Postman**, clique no botão **Import** (canto superior esquerdo).
3.  Selecione o arquivo JSON exportado.
4.  A coleção **"Biblioteca API"** será carregada com todas as pastas e requisições organizadas por cenário (sucesso e erro).

A aplicação deve estar rodando em `http://localhost:8080`. Os testes foram configurados para interagir diretamente com o banco de dados H2 configurado no projeto.

---

## 📌 Observações

* Projeto desenvolvido com foco em aprendizado
* Utiliza banco em memória (H2)
* Não utiliza DTOs neste momento
* Pode ser evoluído com DTOs e testes automatizados
