package dev.luanfernandes.store.repository;

import dev.luanfernandes.store.entity.Produto;

import java.util.List;

public interface CarrinhoRepository {
    void adicionarProduto(int id, int quantidade);

    void excluirProduto(int id);

    List<Produto> listarCarrinho();

    void limparCarrinho();
}
