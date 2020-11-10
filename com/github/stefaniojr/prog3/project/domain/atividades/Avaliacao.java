package com.github.stefaniojr.prog3.project.domain.atividades;

import java.io.Serializable;

import com.github.stefaniojr.prog3.project.domain.Estudante;

public class Avaliacao implements Serializable {
    Estudante estudante;
    private float nota;

    public Avaliacao(Estudante estudante, float nota){
        this.estudante = estudante;
        this.nota = nota;
      }
    
    public Estudante obterAvaliador(){
        return estudante;
    }

    public Estudante obterRef(){
        return obterAvaliador();
    }

    public float obterNota(){
        return nota;
    }
}
