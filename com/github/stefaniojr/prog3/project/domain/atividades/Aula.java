package com.github.stefaniojr.prog3.project.domain.atividades;
import com.github.stefaniojr.prog3.project.domain.Disciplina;

public class Aula extends Atividade {
    private static final long serialVersionUID = 1348633635465464572L;
    private String data;
    
    public Aula(String nome, String sincronismo, Disciplina disciplina, int numero, String data){
        super(nome, sincronismo, disciplina, 2, numero, false);
        this.data = data;
    }

    // Getters.
    public String obterData() {
        return this.data;
    }
}
