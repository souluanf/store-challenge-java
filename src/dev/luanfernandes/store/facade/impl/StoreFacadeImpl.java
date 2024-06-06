package dev.luanfernandes.store.facade.impl;

import dev.luanfernandes.store.entity.Produto;
import dev.luanfernandes.store.facade.StoreFacade;
import dev.luanfernandes.store.service.CarrinhoService;
import dev.luanfernandes.store.service.ProdutoService;
import dev.luanfernandes.store.service.VendaService;
import dev.luanfernandes.store.service.impl.CarrinhoServiceImpl;
import dev.luanfernandes.store.service.impl.ProdutoServiceImpl;
import dev.luanfernandes.store.service.impl.VendaServiceImpl;

import java.util.List;
import java.util.Optional;

public class StoreFacadeImpl implements StoreFacade {
    private static StoreFacadeImpl instance;
    private final ProdutoService produtoService = ProdutoServiceImpl.getInstance();
    private final CarrinhoService carrinhoService = CarrinhoServiceImpl.getInstance();
    private final VendaService vendaService = VendaServiceImpl.getInstance();

    private StoreFacadeImpl() {
    }

    public static synchronized StoreFacadeImpl getInstance() {
        if (instance == null) {
            instance = new StoreFacadeImpl();
        }
        return instance;
    }

    @Override
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @Override
    public void cadastrarProduto(String nome, double valor, int quantidade) {
        produtoService.cadastrarProduto(nome, valor, quantidade);
    }

    @Override
    public void editarProduto(int id, String nome, double valor, int quantidade) {
        produtoService.editarProduto(id, nome, valor, quantidade);
    }

    @Override
    public void excluirProduto(int id) {
        produtoService.excluirProduto(id);
    }

    @Override
    public Optional<Produto> buscarProdutoPorId(int id) {
        return produtoService.buscarPorId(id);
    }

    @Override
    public List<Produto> buscarProdutoPorNome(String nome) {
        return produtoService.buscarProdutoPorNome(nome);
    }

    @Override
    public void adicionarProdutoAoCarrinho(int id, int quantidade) {
        Optional<Produto> produto = buscarProdutoPorId(id);
        if (produto.isPresent()) {
            if (produto.get().quantidade() < quantidade || produto.get().quantidade() == 0) {
                System.out.println("Quantidade indisponível!");
                return;
            }
            carrinhoService.adicionarProdutoAoCarrinho(id, quantidade);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    @Override
    public void excluirProdutoDoCarrinho(int id) {
        carrinhoService.excluirProdutoDoCarrinho(id);
    }

    @Override
    public void finalizarCompraCarrinho() {
        vendaService.finalizarCompraCarrinho();
    }

    @Override
    public void comprarProduto(int id, int quantidade) {
        vendaService.comprarProduto(id, quantidade);
    }

    @Override
    public void limparCarrinho() {
        carrinhoService.limparCarrinho();
    }

    @Override
    public List<Produto> listarCarrinho() {
        return carrinhoService.listarCarrinho();
    }
}