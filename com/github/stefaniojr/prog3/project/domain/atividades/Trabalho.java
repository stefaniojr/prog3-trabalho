package com.github.stefaniojr.prog3.project.domain.atividades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.stefaniojr.prog3.project.domain.Disciplina;

public class Trabalho extends Atividade {
    private static final long serialVersionUID = 134863363546546481L;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date prazo;

    public Trabalho(String nome, String sincronismo, Disciplina disciplina, int numero, String prazo, int nIntegrantes,
            int cargaHoraria, char tipo) throws ParseException {
        super(nome, sincronismo, disciplina, cargaHoraria, numero, true, tipo, prazo);
        this.prazo = df.parse(prazo);
    }

    // Getters.
    public Date obterData() {
        return this.prazo;
    }

}
