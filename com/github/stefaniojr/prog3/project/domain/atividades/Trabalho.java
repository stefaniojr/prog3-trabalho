package com.github.stefaniojr.prog3.project.domain.atividades;

import com.github.stefaniojr.prog3.project.domain.Disciplina;

public class Trabalho extends Atividade {

    private String prazo;

    public Trabalho(String nome, String sincronismo, Disciplina disciplina, int numero, String prazo, int nIntegrantes,
            int cargaHoraria) {
        super(nome, sincronismo, disciplina, cargaHoraria, numero, true);
        this.prazo = prazo;
    }

    // Getters.
    public String obterPrazo() {
        return this.prazo;
    }

}
