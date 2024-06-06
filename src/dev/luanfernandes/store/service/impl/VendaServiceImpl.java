package dev.luanfernandes.store.service.impl;

import dev.luanfernandes.store.entity.Produto;
import dev.luanfernandes.store.service.CarrinhoService;
import dev.luanfernandes.store.service.ProdutoService;
import dev.luanfernandes.store.service.VendaService;

public class VendaServiceImpl implements VendaService {
    private static VendaServiceImpl instance;
    private final ProdutoService produtoService = ProdutoServiceImpl.getInstance();
    private final CarrinhoService carrinhoService = CarrinhoServiceImpl.getInstance();

    private VendaServiceImpl() {
    }

    public static synchronized VendaServiceImpl getInstance() {
        if (instance == null) {
            instance = new VendaServiceImpl();
        }
        return instance;
    }

    @Override
    public void comprarProduto(int id, int quantidade) {
        Produto produto = produtoService.buscarPorId(id).orElse(null);
        if (produto != null && produto.quantidade() >= quantidade) {
            int novaQuantidade = produto.quantidade() - quantidade;
            produtoService.editarProduto(produto.id(), produto.nome(), produto.valor(), novaQuantidade);
            System.out.println("COMPRA REALIZADA COM SUCESSO!");
        } else {
            System.out.println("QUANTIDADE INSUFICIENTE EM ESTOQUE!");
        }
    }

    @Override
    public void finalizarCompraCarrinho() {
        carrinhoService.finalizarCompra();
        System.out.println("COMPRA DO CARRINHO FINALIZADA COM SUCESSO!");
    }
}