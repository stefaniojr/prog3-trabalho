package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;

public class Periodo implements Serializable {
  private static final long serialVersionUID = 1348633635465464576L;
  private int ano;
  private char semestre;

  // Periodo possui um HashMap com referências para as disciplinas a que ele compõe.
  Map<String, Disciplina> disciplinas = new HashMap<>();

  public Periodo(int ano, char semestre){
    this.ano = ano;
    this.semestre = semestre;
  }

  // Getters.
  public String obterRef(){
    return obterAno() + "/" + obterSemestre();
  }

  public int obterAno(){
    return ano;
  }
  
  public char obterSemestre(){
    return semestre;
  }

  // Registro.
  public void adicionarDisciplina(Disciplina disciplina){
    disciplinas.put(disciplina.obterRef(), disciplina);
  }

  // Extras.
  public Map<String, Disciplina> obterDisciplinas (){
    return disciplinas;
  }

}
