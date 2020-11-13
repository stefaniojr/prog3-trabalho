package com.github.stefaniojr.prog3.project.domain.atividades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.stefaniojr.prog3.project.domain.Disciplina;

public class Aula extends Atividade {
    private static final long serialVersionUID = 1348633635465464572L;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
    Date data;

    public Aula(String nome, String sincronismo, Disciplina disciplina, int numero, char tipo, String data)
            throws ParseException {
        super(nome, sincronismo, disciplina, 2, numero, false, tipo, null);
        this.data = df.parse(data);
    }

    // Getters.
    public Date obterData() {
        return this.data;
    }
}
