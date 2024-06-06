package dev.luanfernandes.store.util;

import dev.luanfernandes.store.entity.Produto;

import java.util.List;

public class MenuUtils {
    public static void listarProdutosDinamico(List<Produto> produtos) {
        String[] headers = {"ID", "Nome do produto", "Valor", "Quantidade"};
        int[] widths = new int[headers.length];

        widths[0] = headers[0].length();
        widths[1] = Math.max(headers[1].length(), produtos.stream().mapToInt(p -> p.nome().length()).max().orElse(headers[1].length()));
        widths[2] = Math.max(headers[2].length(), produtos.stream().mapToInt(p -> String.format("%.2f", p.valor()).length()).max().orElse(headers[2].length()));
        widths[3] = Math.max(headers[3].length(), produtos.stream().mapToInt(p -> Integer.toString(p.quantidade()).length()).max().orElse(headers[3].length()));

        String format = "| %" + widths[0] + "s | %-" + widths[1] + "s | %-" + widths[2] + "s | %-" + widths[3] + "s |%n";
        String separator = "+-" + "-".repeat(widths[0]) + "-+-" + "-".repeat(widths[1]) + "-+-" + "-".repeat(widths[2]) + "-+-" + "-".repeat(widths[3]) + "-+";

        System.out.println(separator);
        System.out.printf(format, headers[0], headers[1], headers[2], headers[3]);
        System.out.println(separator);

        for (Produto produto : produtos) {
            System.out.printf(format, produto.id(), produto.nome(), String.format("%.2f", produto.valor()), produto.quantidade());
            System.out.println(separator);
        }
    }
}
