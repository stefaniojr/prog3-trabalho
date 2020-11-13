package com.github.stefaniojr.prog3.project.domain.atividades;

import java.io.Serializable;

import com.github.stefaniojr.prog3.project.domain.Estudante;

public class Avaliacao implements Serializable {
    private static final long serialVersionUID = 1348633635465464569L;
    private Estudante estudante;
    private double nota;

    public Avaliacao(Estudante estudante, double nota) {
        this.estudante = estudante;
        this.nota = nota;
    }

    // Getters.
    public Estudante obterAvaliador() {
        return estudante;
    }

    public Estudante obterRef() {
        return obterAvaliador();
    }

    public double obterNota() {
        return nota;
    }
}
