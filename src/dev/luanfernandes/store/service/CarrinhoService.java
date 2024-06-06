package dev.luanfernandes.store.service;

import dev.luanfernandes.store.entity.Produto;

import java.util.List;

public interface CarrinhoService {
    void adicionarProdutoAoCarrinho(int id, int quantidade);

    void excluirProdutoDoCarrinho(int id);

    List<Produto> listarCarrinho();

    void finalizarCompra();

    void limparCarrinho();
}
