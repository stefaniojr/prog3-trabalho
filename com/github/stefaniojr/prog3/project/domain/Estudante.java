package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import com.github.stefaniojr.prog3.project.domain.atividades.*;


public class Estudante implements Serializable {

  private BigInteger matricula;
  private String nome;

  Map<String, Disciplina> disciplinas = new HashMap<>();
  Map<String, Periodo> periodos = new HashMap<>();
  Map<Atividade, Avaliacao> avaliacoes = new HashMap<>();


  public Estudante(BigInteger matricula, String nome){
    this.matricula = matricula;
    this.nome = nome;
  }

  public BigInteger obterRef(){
    return obterMatricula();
  }

  public BigInteger obterMatricula(){
    return matricula;
  }

  public String obterNome(){
    return nome;
  }

  public float obterMediaDeDisciplinasPorPeriodo(){
    if(periodos.size()!=0){
      return (float)disciplinas.size()/(float)periodos.size();
    }
    else{
      return (float)0;
    }
  }

  public float obterMediaDeAvaliacoesPorDisciplina(){
    if(obterQtDisciplinas()!=0){
      return (float)obterQtAvaliacoes()/(float)obterQtDisciplinas();
    }
    else{
      return (float)0;
    }
  }

  public int obterQtAvaliacoes(){
    return avaliacoes.size();
  }

  public float obterMontanteNotas(){
    float montante = 0;

    for (Atividade chave : avaliacoes.keySet()){
      montante = montante + avaliacoes.get(chave).obterNota();
    }

    return montante;
  }

  public float obterMediaNotas(){
    if(obterQtAvaliacoes()!=0){
      return (float)obterMontanteNotas()/(float)obterQtAvaliacoes();
    }
    else{
      return (float)0;
    }
  }

  public int obterQtDisciplinas(){
    return disciplinas.size();
  }

  public void adicionarDisciplina(Disciplina disciplina){
    disciplinas.put(disciplina.obterRef(), disciplina);
  }

  public void adicionarPeriodo(Periodo periodo){
    periodos.put(periodo.obterRef(), periodo);
  }

  public void adicionarAvaliacao(Atividade atividade, Avaliacao avaliacao){
    avaliacoes.put(atividade, avaliacao);
  }



  // public void exibirDisciplinas(){

  //   info.disciplinasCadastradas(escrever, disciplinas);

  // }

}
