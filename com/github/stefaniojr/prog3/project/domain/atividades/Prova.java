package com.github.stefaniojr.prog3.project.domain.atividades;
import com.github.stefaniojr.prog3.project.domain.Disciplina;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Prova extends Atividade {
    
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
    Date data;

    String conteudo = null;

    public Prova(String nome, String sincronismo, Disciplina disciplina, int numero, String data, String hora, String conteudo){
        super(nome, sincronismo, disciplina, 2, numero, true);
        
        try{
            this.data = df.parse(data + "-" + hora);
        } catch (ParseException e){
            e.printStackTrace();
        }
        this.conteudo = conteudo;
        
    }
}
