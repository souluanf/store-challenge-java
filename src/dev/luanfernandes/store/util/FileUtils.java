package dev.luanfernandes.store.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static void criarArquivoSeNaoExistir(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.getParentFile() != null && !file.getParentFile().exists()) {
                    boolean directoriesCreated = file.getParentFile().mkdirs();
                    if (!directoriesCreated) {
                        System.err.println("Erro ao criar diretórios para o arquivo de produtos.");
                        return;
                    }
                }
                boolean fileCreated = file.createNewFile();
                if (!fileCreated) {
                    System.err.println("Erro ao criar arquivo de produtos.");
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo de produtos: " + e.getMessage());
            }
        }
    }
}