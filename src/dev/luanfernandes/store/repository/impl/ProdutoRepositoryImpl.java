package dev.luanfernandes.store.repository.impl;

import dev.luanfernandes.store.entity.Produto;
import dev.luanfernandes.store.repository.ProdutoRepository;
import dev.luanfernandes.store.util.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    private static final String PRODUCT_FILE_PATH = "data/produtos.txt";
    private static ProdutoRepositoryImpl instance;

    private ProdutoRepositoryImpl() {
        FileUtils.criarArquivoSeNaoExistir(PRODUCT_FILE_PATH);
    }

    public static synchronized ProdutoRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ProdutoRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Produto produto = new Produto(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        Double.parseDouble(dados[2]),
                        Integer.parseInt(dados[3])
                );
                produtos.add(produto);
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    @Override
    public void salvar(Produto produto) {
        List<Produto> produtos = listarTodos();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCT_FILE_PATH))) {
            boolean produtoExistente = false;
            for (Produto p : produtos) {
                if (p.id() == produto.id()) {
                    produtoExistente = true;
                    bw.write(formatarProduto(produto));
                } else {
                    bw.write(formatarProduto(p));
                }
            }
            if (!produtoExistente) {
                bw.write(formatarProduto(produto));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar produto: " + e.getMessage());
        }
    }

    @Override
    public Optional<Produto> buscarPorId(int id) {
        return listarTodos().stream().filter(p -> p.id() == id).findFirst();
    }

    @Override
    public boolean excluir(int id) {
        List<Produto> produtos = listarTodos();
        boolean produtoExistente = produtos.removeIf(p -> p.id() == id);
        if (produtoExistente) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCT_FILE_PATH))) {
                for (Produto p : produtos) {
                    bw.write(formatarProduto(p));
                }
            } catch (IOException e) {
                System.err.println("Erro ao excluir produto: " + e.getMessage());
            }
        }
        return produtoExistente;
    }

    @Override
    public int obterProximoId() {
        return listarTodos().stream().mapToInt(Produto::id).max().orElse(0) + 1;
    }

    private String formatarProduto(Produto produto) {
        return String.format("%d;%s;%.2f;%d%n", produto.id(), produto.nome(), produto.valor(), produto.quantidade());
    }
}