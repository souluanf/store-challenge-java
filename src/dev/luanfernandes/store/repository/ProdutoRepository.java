package dev.luanfernandes.store.repository;

import dev.luanfernandes.store.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    List<Produto> listarTodos();

    void salvar(Produto produto);

    Optional<Produto> buscarPorId(int id);

    boolean excluir(int id);

    int obterProximoId();
}
