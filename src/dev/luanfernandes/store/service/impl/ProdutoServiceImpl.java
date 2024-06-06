package dev.luanfernandes.store.service.impl;

import dev.luanfernandes.store.entity.Produto;
import dev.luanfernandes.store.repository.ProdutoRepository;
import dev.luanfernandes.store.repository.impl.ProdutoRepositoryImpl;
import dev.luanfernandes.store.service.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService {
    private static ProdutoServiceImpl instance;
    private final ProdutoRepository produtoRepository = ProdutoRepositoryImpl.getInstance();

    private ProdutoServiceImpl() {
    }

    public static synchronized ProdutoServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProdutoServiceImpl();
        }
        return instance;
    }

    @Override
    public void cadastrarProduto(String nome, double valor, int quantidade) {
        Produto produto = new Produto(produtoRepository.obterProximoId(), nome, valor, quantidade);
        produtoRepository.salvar(produto);
        System.out.println("PRODUTO SALVO!");
    }
}