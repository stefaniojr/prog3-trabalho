package com.github.stefaniojr.prog3.project.domain.atividades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.stefaniojr.prog3.project.domain.Disciplina;

public class Prova extends Atividade {
    private static final long serialVersionUID = 1348633635465464571L;
    private String conteudo = null;

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat hf = new SimpleDateFormat("HH:mm");
    Date data;
    Date hora;

    public Prova(String nome, String sincronismo, Disciplina disciplina, int numero, String data, String hora,
            String conteudo, char tipo) throws ParseException {
        super(nome, sincronismo, disciplina, 2, numero, true, tipo, data);
        this.data = df.parse(data);
        this.hora = hf.parse(hora);
        this.conteudo = conteudo;

    }

    // Getters.
    public Date obterData() {
        return this.data;
    }

    public Date obterHora() {
        return this.hora;
    }

    public String obterConteudo() {
        return this.conteudo;
    }
}
