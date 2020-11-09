package com.github.stefaniojr.prog3.project.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Leitura {

    // Método responsável por ler de uma planilha dados e salvá-los em um vetor de
    // String e, então, truncá-la em diferentes itens de um vetor de String.
    public String[] planilha(File arq) throws IOException, ParseException, FileNotFoundException {
        try (Scanner scanner = new Scanner(arq, "UTF-8")) {
            // Cabeçalho...
            String dados = scanner.nextLine();
            // Ler string completa
            dados = scanner.useDelimiter("\\A").next().trim();
            // Trunca string, o parâmetro -1 garante que espaços vazios também serão
            // capturados.
            String[] dadosTruncados = dados.split(";|\n", -1);
            return dadosTruncados;
        }
    }

}
