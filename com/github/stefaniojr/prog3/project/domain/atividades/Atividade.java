package com.github.stefaniojr.prog3.project.domain.atividades;

import java.io.Serializable;
import java.util.*;
import com.github.stefaniojr.prog3.project.domain.*;

public abstract class Atividade implements Serializable {
  
  private String nome;
  private String sincronismo;
  private Disciplina disciplina;
  private int numero;
  private int cargaHoraria;
  private boolean avaliativa;

  // Atividade possui uma lista de avaliações.
  List<Avaliacao> avaliacoes = new ArrayList<>();

  public Atividade(String nome, String sincronismo, Disciplina disciplina, int cargaHoraria, int numero, boolean avaliativa) {
    this.nome = nome;
    this.sincronismo = sincronismo;
    this.disciplina = disciplina;
    this.numero = numero;
    this.cargaHoraria = cargaHoraria;
    this.avaliativa = avaliativa;
  }

  // Getters.
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

  public int obterNumeroSequencial(){
    return this.numero;
  }

  public Disciplina obterDisciplina() {
    return this.disciplina;
  }

  // Setters.
  public void avaliarAtividade(Estudante estudante, float nota) {
    avaliacoes.add(new Avaliacao(estudante, nota));
  }

  // Estatísticas.
  public float obterMontanteNotasAvaliacoes() {
    float montanteNotas = 0;
    for (Avaliacao avaliacao : avaliacoes) {
      montanteNotas = montanteNotas + avaliacao.obterNota();
    }
    return montanteNotas;
  }

  public Avaliacao encontrarAvaliacao(Estudante estudante){
    for (Avaliacao avaliacao : avaliacoes) {
      if (estudante.obterNome().equals(avaliacao.obterAvaliador().obterNome())){
        return avaliacao;
      }
    }
    return null;
  }

  // Extras.
  public boolean isAvaliativa(){
    return this.avaliativa;
  }

}
