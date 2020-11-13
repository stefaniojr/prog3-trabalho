package com.github.stefaniojr.prog3.project.domain.atividades;

import java.text.ParseException;

import com.github.stefaniojr.prog3.project.domain.Disciplina;

public class Estudo extends Atividade {
    private static final long serialVersionUID = 1348633635465464570L;
    private String conteudo = null;

    public Estudo(String nome, String sincronismo, Disciplina disciplina, int numero, String conteudo, char tipo)
            throws ParseException {
        super(nome, sincronismo, disciplina, 2, numero, false, tipo, null);
        this.conteudo = conteudo;
    }

    // Getters.
    public String obterConteudo() {
        return this.conteudo;
    }

}
