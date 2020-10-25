package com.github.stefaniojr.prog3.project.domain;

import java.util.*;

public class Estudo extends Atividade {

    Map<String, String> conteudos = new HashMap<>();

    public Estudo(String nome, String sincronismo, Disciplina disciplina, int numero, Map<String, String> conteudos) {
        super(nome, sincronismo, disciplina, 2, numero, false);

        this.conteudos = conteudos;
    }
}
