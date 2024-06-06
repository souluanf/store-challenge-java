package dev.luanfernandes.store.service;

import dev.luanfernandes.store.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    void cadastrarProduto(String nome, double valor, int quantidade);

    void editarProduto(int id, String nome, double valor, int quantidade);

    void excluirProduto(int id);

    List<Produto> listarProdutos();

    Optional<Produto> buscarPorId(int id);

    List<Produto> buscarProdutoPorNome(String nome);
}
