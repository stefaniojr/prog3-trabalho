package com.github.stefaniojr.prog3.project.domain.atividades;

import com.github.stefaniojr.prog3.project.domain.Disciplina;

public class Estudo extends Atividade {

    private String conteudo = null;

    public Estudo(String nome, String sincronismo, Disciplina disciplina, int numero, String conteudo) {
        super(nome, sincronismo, disciplina, 2, numero, false);
        this.conteudo = conteudo;
    }

    // Getters.
    public String obterConteudo() {
        return this.conteudo;
    }
}
