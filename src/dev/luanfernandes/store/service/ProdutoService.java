package dev.luanfernandes.store.service;

public interface ProdutoService {
    void cadastrarProduto(String nome, double valor, int quantidade);

    void editarProduto(int id, String nome, double valor, int quantidade);

    void excluirProduto(int id);
}
