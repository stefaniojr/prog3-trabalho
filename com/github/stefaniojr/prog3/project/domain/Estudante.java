package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import com.github.stefaniojr.prog3.project.domain.atividades.*;

public class Estudante implements Serializable {

  private BigInteger matricula;
  private String nome;

  // Estudante possui um HashMap com referências para os periodos em que ele está
  // matriculado.
  private Map<String, Periodo> periodos = new HashMap<>();
  // Estudante possui um HashMap com referências para as disciplinas em que ele
  // está matriculado.
  private Map<String, Disciplina> disciplinas = new HashMap<>();
  // Estudante possui um HashMap com referências para as avaliações que ele
  // realizou.
  private Map<Atividade, Avaliacao> avaliacoes = new HashMap<>();

  public Estudante(BigInteger matricula, String nome) {
    this.matricula = matricula;
    this.nome = nome;
  }

  // Getters.
  public BigInteger obterRef() {
    return obterMatricula();
  }

  public BigInteger obterMatricula() {
    return matricula;
  }

  public String obterNome() {
    return nome;
  }

  public int obterQtDisciplinas() {
    return disciplinas.size();
  }

  public int obterQtAvaliacoes() {
    return avaliacoes.size();
  }

  // Setters.
  public void adicionarDisciplina(Disciplina disciplina) {
    disciplinas.put(disciplina.obterRef(), disciplina);
  }

  public void adicionarPeriodo(Periodo periodo) {
    periodos.put(periodo.obterRef(), periodo);
  }

  public void adicionarAvaliacao(Atividade atividade, Avaliacao avaliacao) {
    avaliacoes.put(atividade, avaliacao);
  }

  // Estatísticas.
  public float obterMediaDeDisciplinasPorPeriodo() {
    if (periodos.size() != 0) {
      return (float) disciplinas.size() / (float) periodos.size();
    } else {
      return (float) 0;
    }
  }

  public float obterMediaDeAvaliacoesPorDisciplina() {
    if (obterQtDisciplinas() != 0) {
      return (float) obterQtAvaliacoes() / (float) obterQtDisciplinas();
    } else {
      return (float) 0;
    }
  }

  public float obterMontanteNotas() {
    float montante = 0F;

    for (Atividade chave : avaliacoes.keySet()) {
      montante = montante + avaliacoes.get(chave).obterNota();
    }

    return montante;
  }

  public float obterMediaNotas() {
    if (obterQtAvaliacoes() != 0) {
      return (float) obterMontanteNotas() / (float) obterQtAvaliacoes();
    } else {
      return (float) 0;
    }
  }

}
