package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;
import com.github.stefaniojr.prog3.project.io.Escrita;


public abstract class Atividade implements Serializable {
  
  String nome;
  String sincronismo;
  Disciplina disciplina;
  int numero;
  int cargaHoraria;
  boolean avaliativa;

  List<Avaliacao> avaliacoes = new ArrayList<>();

  Escrita escrever = new Escrita();

  public Atividade(String nome, String sincronismo, Disciplina disciplina, int cargaHoraria, int numero, boolean avaliativa) {
    this.nome = nome;
    this.sincronismo = sincronismo;
    this.disciplina = disciplina;
    this.numero = numero;
    this.cargaHoraria = cargaHoraria;
    this.avaliativa = avaliativa;
  }

  public int obterRef() {
    return numero;
  }

  public String obterNome() {
    return this.nome;
  }

  public String obterSincronismo() {
    return this.sincronismo;
  }

  public int obterQtAvaliadores() {
    return avaliacoes.size();
  }

  public int obterCargaHoraria() {
    return this.cargaHoraria;
  }

  public boolean isAvaliativa(){
    return this.avaliativa;
  }

  public int obterNumeroSequencial(){
    return this.numero;
  }

  public float obterMontanteNotasAvaliacoes() {
    float montanteNotas = 0;
    for (Avaliacao avaliacao : avaliacoes) {
      montanteNotas = montanteNotas + avaliacao.obterNota();
    }
    return montanteNotas;
  }

  public Disciplina obterDisciplina() {
    return this.disciplina;
  }

  public void avaliarAtividade(Estudante estudante, float nota) {
    avaliacoes.add(new Avaliacao(estudante, nota));
  }

  public Avaliacao encontrarAvaliacao(Estudante estudante){
    for (Avaliacao avaliacao : avaliacoes) {
      if (estudante.obterNome().equals(avaliacao.obterAvaliador().obterNome())){
        return avaliacao;
      }
    }
    return null;
  }

  // public void exibirAvaliacoes() {
  // info.avaliacoesCadastradas(escrever, avaliacoes);
  // }

}
