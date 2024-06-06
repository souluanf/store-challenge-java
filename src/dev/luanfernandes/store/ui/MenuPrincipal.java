package dev.luanfernandes.store.ui;

import dev.luanfernandes.store.entity.Produto;
import dev.luanfernandes.store.facade.StoreFacade;
import dev.luanfernandes.store.facade.impl.StoreFacadeImpl;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private final StoreFacade storeFacade = StoreFacadeImpl.getInstance();

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("############ MENU ##################");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Cadastrar novo produto");
            System.out.println("3 - Editar produto");
            System.out.println("4 - Excluir produto");
            System.out.println("5 - Buscar produto por nome");
            System.out.println("6 - Buscar produto por ID");
            System.out.println("7 - Carrinho");
            System.out.println("8 - Comprar Produto");
            System.out.println("9 - Sair");
            System.out.print("DIGITE UMA OPÇÃO: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> listarProdutos();
                case 2 -> cadastrarProduto(scanner);
                case 3 -> editarProduto(scanner);
                case 4 -> excluirProduto(scanner);
                case 5 -> buscarProdutoPorNome(scanner);
                case 6 -> buscarProdutoPorId(scanner);
                case 8 -> exibirMenuVenda(scanner);
                case 9 -> {
                    System.out.println("SAINDO...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void listarProdutos() {
        System.out.println();
        System.out.println("############ LISTA DE PRODUTOS ##################");
        List<Produto> produtos = storeFacade.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("NENHUM PRODUTO CADASTRADO!");
        } else {
            this.listarProdutosDinamico(produtos);
        }
    }

    private void cadastrarProduto(Scanner scanner) {
        System.out.println();
        System.out.println("############ CADASTRO DE PRODUTO ##################");

        System.out.print("DIGITE O NOME DO PRODUTO: ");
        String nome = scanner.nextLine();

        System.out.print("DIGITE O VALOR DO PRODUTO: ");
        String valorInput = scanner.nextLine();
        double valor = Double.parseDouble(valorInput.replace(",", "."));

        System.out.print("DIGITE A QUANTIDADE DO PRODUTO: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        storeFacade.cadastrarProduto(nome, valor, quantidade);
    }

    private void editarProduto(Scanner scanner) {
        System.out.println();
        System.out.println("############ EDIÇÃO DE PRODUTO ##################");
        System.out.print("DIGITE O ID DO PRODUTO: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("DIGITE O NOME DO PRODUTO: ");
        String nome = scanner.nextLine();
        System.out.print("DIGITE O VALOR DO PRODUTO: ");
        String valorInput = scanner.nextLine();
        double valor = Double.parseDouble(valorInput.replace(",", "."));
        System.out.print("DIGITE A QUANTIDADE DO PRODUTO: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        storeFacade.editarProduto(id, nome, valor, quantidade);
        System.out.println("PRODUTO EDITADO COM SUCESSO!");
    }

    private void excluirProduto(Scanner scanner) {
        System.out.println();
        System.out.println("############ EXCLUSÃO DE PRODUTO ##################");
        System.out.print("DIGITE O ID DO PRODUTO: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        storeFacade.excluirProduto(id);
        System.out.println("PRODUTO EXCLUIDO COM SUCESSO!");
    }

    private void buscarProdutoPorNome(Scanner scanner) {
        System.out.println();
        System.out.println("############ BUSCA DE PRODUTO POR NOME ##################");
        System.out.print("DIGITE O NOME DO PRODUTO: ");
        String nome = scanner.nextLine();
        List<Produto> produtos = storeFacade.buscarProdutoPorNome(nome);
        if (!produtos.isEmpty()) {
            System.out.println("+----+---------------------+---------------------+---------------------+");
            System.out.println("| ID | Nome do produto     | Valor               | Quantidade          |");
            System.out.println("+----+---------------------+---------------------+---------------------+");
            produtos.forEach(produto -> {
                System.out.printf("| %-2d | %-19s | %-19.2f | %-19d |%n", produto.id(), produto.nome(), produto.valor(), produto.quantidade());
                System.out.println("+----+---------------------+---------------------+---------------------+");
            });
        }
    }

    private void buscarProdutoPorId(Scanner scanner) {
        System.out.println();
        System.out.println("############ BUSCA DE PRODUTO POR ID ##################");
        System.out.print("DIGITE O ID DO PRODUTO: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        storeFacade.buscarProdutoPorId(id).ifPresentOrElse(
                produto -> {
                    System.out.println("+----+---------------------+---------------------+---------------------+");
                    System.out.println("| ID | Nome do produto     | Valor               | Quantidade          |");
                    System.out.println("+----+---------------------+---------------------+---------------------+");
                    System.out.printf("| %-2d | %-19s | %-19.2f | %-19d |%n", produto.id(), produto.nome(), produto.valor(), produto.quantidade());
                    System.out.println("+----+---------------------+---------------------+---------------------+");
                },
                () -> System.out.println("PRODUTO NÃO ENCONTRADO!")
        );
    }

    private void exibirMenuVenda(Scanner scanner) {
        System.out.println();
        System.out.println("############ VENDA DE PRODUTO ##################");
        System.out.print("DIGITE O ID DO PRODUTO: ");
        int id = scanner.nextInt();
        System.out.print("DIGITE A QUANTIDADE: ");
        int quantidade = scanner.nextInt();
        storeFacade.comprarProduto(id, quantidade);
    }

    private void listarProdutosDinamico(List<Produto> produtos) {
        String[] headers = {"ID", "Nome do produto", "Valor", "Quantidade"};
        int[] widths = new int[headers.length];

        widths[0] = headers[0].length();
        widths[1] = Math.max(headers[1].length(), produtos.stream().mapToInt(p -> p.nome().length()).max().orElse(headers[1].length()));
        widths[2] = Math.max(headers[2].length(), produtos.stream().mapToInt(p -> String.format("%.2f", p.valor()).length()).max().orElse(headers[2].length()));
        widths[3] = Math.max(headers[3].length(), produtos.stream().mapToInt(p -> Integer.toString(p.quantidade()).length()).max().orElse(headers[3].length()));

        String format = "| %" + widths[0] + "s | %-" + widths[1] + "s | %-" + widths[2] + "s | %-" + widths[3] + "s |%n";
        String separator = "+-" + "-".repeat(widths[0]) + "-+-" + "-".repeat(widths[1]) + "-+-" + "-".repeat(widths[2]) + "-+-" + "-".repeat(widths[3]) + "-+";

        System.out.println(separator);
        System.out.printf(format, headers[0], headers[1], headers[2], headers[3]);
        System.out.println(separator);

        for (Produto produto : produtos) {
            System.out.printf(format, produto.id(), produto.nome(), String.format("%.2f", produto.valor()), produto.quantidade());
            System.out.println(separator);
        }
    }

}