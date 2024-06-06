package dev.luanfernandes.store.service.impl;

import dev.luanfernandes.store.entity.Produto;
import dev.luanfernandes.store.repository.CarrinhoRepository;
import dev.luanfernandes.store.repository.impl.CarrinhoRepositoryImpl;
import dev.luanfernandes.store.service.CarrinhoService;
import dev.luanfernandes.store.service.ProdutoService;

import java.util.List;

public class CarrinhoServiceImpl implements CarrinhoService {
    private static CarrinhoServiceImpl instance;
    private final ProdutoService produtoService = ProdutoServiceImpl.getInstance();
    private final CarrinhoRepository carrinhoRepository = CarrinhoRepositoryImpl.getInstance();

    private CarrinhoServiceImpl() {
    }

    public static synchronized CarrinhoServiceImpl getInstance() {
        if (instance == null) {
            instance = new CarrinhoServiceImpl();
        }
        return instance;
    }

    @Override
    public void adicionarProdutoAoCarrinho(int id, int quantidade) {
        carrinhoRepository.adicionarProduto(id, quantidade);
    }

    @Override
    public void excluirProdutoDoCarrinho(int id) {
        carrinhoRepository.excluirProduto(id);
    }

    @Override
    public List<Produto> listarCarrinho() {
        return carrinhoRepository.listarCarrinho();
    }

    @Override
    public void finalizarCompra() {
        List<Produto> carrinho = listarCarrinho();
        for (Produto produtoCarrinho : carrinho) {
            Produto produtoEstoque = produtoService.buscarPorId(produtoCarrinho.id()).orElse(null);
            if (produtoEstoque != null) {
                int novaQuantidade = produtoEstoque.quantidade() - produtoCarrinho.quantidade();
                Produto produtoAtualizado = new Produto(produtoEstoque.id(), produtoEstoque.nome(), produtoEstoque.valor(), novaQuantidade);
                produtoService.editarProduto(produtoAtualizado.id(), produtoAtualizado.nome(), produtoAtualizado.valor(), produtoAtualizado.quantidade());
            }
        }
        limparCarrinho();
    }

    @Override
    public void limparCarrinho() {
        carrinhoRepository.limparCarrinho();
    }
}