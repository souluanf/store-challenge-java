# Sistema de Cadastro de Produtos

## Descrição

O sistema é uma aplicação desktop sem interface gráfica, onde toda a interação é feita via terminal. O projeto foi desenvolvido utilizando princípios de orientação a objetos, design patterns, algoritmos de busca e ordenação, novas features do Java (como Streams, records e sequence collections) e princípios SOLID.

## Funcionalidades

1. **Menu Principal**
    - Listar produtos
    - Cadastrar novo produto
    - Editar produto
    - Excluir produto
    - Buscar produto por nome
    - Buscar produto por ID
    - Carrinho
    - Comprar Produto
    - Sair

2. **Listagem de Produtos**
    - Alinhamento entre os diversos produtos conforme especificado.+
    - A largura das colunas é ajustada conforme o tamanho do nome e preço e a quantidade de produtos.
    - Exibição de mensagem caso não haja produtos cadastrados.

3. **Cadastro de Produto**
    - Salvar produto em arquivo .txt.
    - Mensagem de sucesso ao cadastrar.

4. **Edição de Produto**
    - Editar produto pelo ID.
    - Mensagem de sucesso ao editar.

5. **Exclusão de Produto**
    - Excluir produto pelo ID.
    - Mensagem de sucesso ao excluir.

6. **Carrinho de Compras (Bônus)**
    - Adicionar produto ao carrinho.
    - Excluir produto do carrinho.
    - Finalizar compra.
    - Mensagem de sucesso em cada operação.

7. **Venda de Produtos (Bônus)**
    - Comprar produto pelo ID e quantidade.
    - Subtrair da quantidade de produto disponível.
    - Mensagem de sucesso ao comprar.

## Tecnologias e Práticas Utilizadas

### Orientação a Objetos (OO)
- Uso de classes, objetos e encapsulamento.
- Classes de serviço (`ProdutoService`, `CarrinhoService`, `VendaService`) para manipulação de dados.
- Entidades representadas por `Produto`.

### Design Patterns
- **Singleton**: Garantir que apenas uma instância de cada serviço seja criada.
- **Facade**: Simplificar a interação com os serviços através da `StoreFacade`.

### Algoritmos de Busca e Ordenação
- Uso de Streams para buscar e ordenar produtos de maneira eficiente.

### Novas Features do Java
- **Streams**: Para processamento de coleções de produtos.
- **Records**: Para representar a entidade `Produto`.
- **Sequence Collections**: Uso de `List.of` para criar listas imutáveis.

### SOLID
- **Single Responsibility Principle (SRP)**: Cada classe tem uma única responsabilidade.
- **Open/Closed Principle (OCP)**: O design permite a extensão sem modificação.
- **Liskov Substitution Principle (LSP)**: As classes de serviço implementam interfaces.
- **Interface Segregation Principle (ISP)**: Interfaces são coesas e específicas.
- **Dependency Inversion Principle (DIP)**: Dependência de abstrações em vez de implementações concretas.

## Estrutura do Projeto

```plaintext
src/
└── dev/
    └── luanfernandes/
        └── store/
            ├── entity/
            │   └── Produto.java
            ├── facade/
            │   ├── StoreFacade.java
            │   └── impl/
            │       └── StoreFacadeImpl.java
            ├── repository/
            │   ├── CarrinhoRepository.java
            │   ├── ProdutoRepository.java
            │   └── impl/
            │       ├── CarrinhoRepositoryImpl.java
            │       └── ProdutoRepositoryImpl.java
            ├── service/
            │   ├── CarrinhoService.java
            │   ├── ProdutoService.java
            │   ├── VendaService.java
            │   └── impl/
            │       ├── CarrinhoServiceImpl.java
            │       ├── ProdutoServiceImpl.java
            │       └── VendaServiceImpl.java
            ├── ui/
            │   ├── MenuCarrinho.java
            │   └── MenuPrincipal.java
            ├── util/
            │   └── FileUtils.java
            └── Application.java
```

## Executando a Aplicação

Clone o repositório e acesse o diretório do projeto:

```sh
git clone https://github.com/souluanf/store-challenge-java.git
cd store-challenge-java
```

Para compilar e executar a aplicação, siga os passos abaixo:

1. Compile o projeto e gere o arquivo JAR:
   ```sh
   mvn clean package
   ```

2. Execute a aplicação:
   ```sh
   java -jar target/store-challenge-java.jar
   ```

## Como Utilizar

1. **Listar produtos**: Exibe a lista de produtos cadastrados.
2. **Cadastrar novo produto**: Solicita informações do produto e o cadastra.
3. **Editar produto**: Edita um produto existente pelo ID.
4. **Excluir produto**: Exclui um produto existente pelo ID.
5. **Buscar produto por nome**: Busca produtos pelo nome.
6. **Buscar produto por ID**: Busca um produto pelo ID.
7. **Carrinho**: Adiciona, lista, remove produtos do carrinho e finaliza a compra.
8. **Comprar Produto**: Realiza a compra de um produto específico.
