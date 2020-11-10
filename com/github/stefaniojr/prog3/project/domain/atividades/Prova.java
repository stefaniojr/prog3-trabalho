package com.github.stefaniojr.prog3.project.domain.atividades;

import com.github.stefaniojr.prog3.project.domain.Disciplina;

public class Prova extends Atividade {
    private static final long serialVersionUID = 1348633635465464571L;
    private String data;
    private String hora;
    private String conteudo = null;

    public Prova(String nome, String sincronismo, Disciplina disciplina, int numero, String data, String hora,
            String conteudo) {
        super(nome, sincronismo, disciplina, 2, numero, true);
        this.data = data;
        this.hora = hora;
        this.conteudo = conteudo;

    }

    // Getters.
    public String obterData(){
        return this.data;
    }

    public String obterHora(){
        return this.hora;
    }

    public String obterConteudo(){
        return this.conteudo;
    }
}
