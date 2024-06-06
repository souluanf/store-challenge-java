package dev.luanfernandes.store.service.impl;

import dev.luanfernandes.store.entity.Produto;
import dev.luanfernandes.store.repository.ProdutoRepository;
import dev.luanfernandes.store.repository.impl.ProdutoRepositoryImpl;
import dev.luanfernandes.store.service.ProdutoService;

import java.util.Optional;

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

    @Override
    public void editarProduto(int id, String nome, double valor, int quantidade) {
        Optional<Produto> produtoOptional = produtoRepository.buscarPorId(id);
        if (produtoOptional.isPresent()) {
            Produto produto = new Produto(id, nome, valor, quantidade);
            produtoRepository.salvar(produto);
            System.out.println("PRODUTO EDITADO COM SUCESSO!");
        } else {
            System.out.println("PRODUTO NÃO ENCONTRADO!");
        }
    }
}