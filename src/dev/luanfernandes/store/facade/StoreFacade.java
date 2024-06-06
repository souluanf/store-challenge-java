package dev.luanfernandes.store.facade;

import dev.luanfernandes.store.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface StoreFacade {
    List<Produto> listarProdutos();

    void cadastrarProduto(String nome, double valor, int quantidade);

    void editarProduto(int id, String nome, double valor, int quantidade);

    void excluirProduto(int id);

    Optional<Produto> buscarProdutoPorId(int id);

    List<Produto> buscarProdutoPorNome(String nome);

    void adicionarProdutoAoCarrinho(int id, int quantidade);

    void excluirProdutoDoCarrinho(int id);

    void finalizarCompraCarrinho();

    void comprarProduto(int id, int quantidade);
    void limparCarrinho();
    List<Produto> listarCarrinho();
}
