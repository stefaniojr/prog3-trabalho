package com.github.stefaniojr.prog3.project.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import com.github.stefaniojr.prog3.project.domain.Cadastro;

public class Leitura {

    Scanner in;
    Cadastro cadastro = new Cadastro();

    public void iniciarLeitura() {
        this.in = new Scanner(System.in);
        in.useLocale(Locale.US);
    }

    public Scanner leitor() {
        return in;
    }

    public int inteiro() {
        return this.in.nextInt();
    }

    public float flutuante() {
        return this.in.nextFloat();
    }

    public String cadeiaCaract() {
        return this.in.nextLine();
    }

    public char caract() {
        return this.in.next().charAt(0);
    }

    public String linha() {
        return this.in.nextLine();
    }

    public void finalizarLeitura() {
        this.in.close();
    }

    public String[] planilha(File arq) throws IOException, ParseException, FileNotFoundException {
        try (Scanner scanner = new Scanner(arq, "UTF-8")) {
            String dados = scanner.nextLine(); // Cabeçalho...
            dados = scanner.useDelimiter("\\A").next().trim(); // Ler string completa
            String[] dadosTruncados = dados.split(";|\n", -1); // Trunca string, o parâmetro -1 garante que espaços vazios também serão capturados.
            return dadosTruncados;
        }
    }

}
