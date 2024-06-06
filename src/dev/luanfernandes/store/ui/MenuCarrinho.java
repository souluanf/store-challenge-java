package dev.luanfernandes.store.ui;

import dev.luanfernandes.store.entity.Produto;
import dev.luanfernandes.store.facade.StoreFacade;
import dev.luanfernandes.store.facade.impl.StoreFacadeImpl;

import java.util.List;
import java.util.Scanner;

import static dev.luanfernandes.store.util.MenuUtils.listarProdutosDinamico;

public class MenuCarrinho {
    private final StoreFacade storeFacade = StoreFacadeImpl.getInstance();

    public void exibirMenuCarrinho(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("########### CARRINHO #################");
            System.out.println("1 - Adicionar produto ao carrinho");
            System.out.println("2 - Excluir produto do carrinho");
            System.out.println("3 - Comprar todos os itens do carrinho");
            System.out.println("4 - Limpar carrinho");
            System.out.println("5 - Listar itens do carrinho");
            System.out.println("6 - Sair do carrinho");
            System.out.print("DIGITE UMA OPÇÃO: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> {
                    System.out.print("DIGITE O ID DO PRODUTO: ");
                    int idAdicionar = scanner.nextInt();
                    System.out.print("DIGITE A QUANTIDADE: ");
                    int quantidadeAdicionar = scanner.nextInt();
                    storeFacade.adicionarProdutoAoCarrinho(idAdicionar, quantidadeAdicionar);
                }
                case 2 -> {
                    System.out.print("DIGITE O ID DO PRODUTO: ");
                    int idExcluir = scanner.nextInt();
                    storeFacade.excluirProdutoDoCarrinho(idExcluir);
                }
                case 3 -> storeFacade.finalizarCompraCarrinho();
                case 4 -> storeFacade.limparCarrinho();
                case 5 -> listarItensDoCarrinho();
                case 6 -> {
                    System.out.println("SAINDO DO CARRINHO...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void listarItensDoCarrinho() {
        System.out.println();
        System.out.println("############ LISTA DO CARRINHO ##################");
        List<Produto> produtos = storeFacade.listarCarrinho();
        if (produtos.isEmpty()) {
            System.out.println("CARRINHO VAZIO!");
        } else {
            listarProdutosDinamico(produtos);
        }
    }
}
