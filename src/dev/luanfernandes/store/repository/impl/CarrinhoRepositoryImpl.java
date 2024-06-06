package dev.luanfernandes.store.repository.impl;

import dev.luanfernandes.store.entity.Produto;
import dev.luanfernandes.store.repository.CarrinhoRepository;
import dev.luanfernandes.store.service.ProdutoService;
import dev.luanfernandes.store.service.impl.ProdutoServiceImpl;
import dev.luanfernandes.store.util.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoRepositoryImpl implements CarrinhoRepository {
    private static final String CART_FILE_PATH = "data/carrinho.txt";
    private static CarrinhoRepositoryImpl instance;
    private final ProdutoService produtoService = ProdutoServiceImpl.getInstance();

    private CarrinhoRepositoryImpl() {
        FileUtils.criarArquivoSeNaoExistir(CART_FILE_PATH);
    }

    public static synchronized CarrinhoRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CarrinhoRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void adicionarProduto(int id, int quantidade) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CART_FILE_PATH, true))) {
            bw.write(id + ";" + quantidade + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Erro ao adicionar produto ao carrinho: " + e.getMessage());
        }
    }

    @Override
    public void excluirProduto(int id) {
        List<String> carrinho = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CART_FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) != id) {
                    carrinho.add(linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao excluir produto do carrinho: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CART_FILE_PATH))) {
            for (String item : carrinho) {
                bw.write(item + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar carrinho: " + e.getMessage());
        }
    }

    @Override
    public List<Produto> listarCarrinho() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CART_FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                int quantidade = Integer.parseInt(dados[1]);
                produtoService.buscarPorId(id).ifPresent(produto -> produtos.add(new Produto(produto.id(), produto.nome(), produto.valor(), quantidade)));
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar produtos do carrinho: " + e.getMessage());
        }
        return produtos;
    }

    @Override
    public void limparCarrinho() {
        try (BufferedWriter ignored = new BufferedWriter(new FileWriter(CART_FILE_PATH))) {
            ignored.write("");
        } catch (IOException e) {
            System.err.println("Erro ao limpar carrinho: " + e.getMessage());
        }
    }
}