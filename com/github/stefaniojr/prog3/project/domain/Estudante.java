package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import java.text.Collator;
import java.lang.Comparable;
import com.github.stefaniojr.prog3.project.domain.atividades.*;

public class Estudante implements Serializable, Comparable<Estudante> {
  private static final long serialVersionUID = 1348633635465464578L;
  private BigInteger matricula;
  private String nome;

  // Estudante possui um HashMap com referências para os periodos em que ele está
  // matriculado.
  Map<String, Periodo> periodos = new HashMap<>();
  // Estudante possui um HashMap com referências para as disciplinas em que ele
  // está matriculado.
  Map<String, Disciplina> disciplinas = new HashMap<>();
  // Estudante possui um HashMap com referências para as avaliações que ele
  // realizou.
  Map<Atividade, Avaliacao> avaliacoes = new HashMap<>();

  Locale locale = new Locale("pt", "BR");
  private transient Collator collatorInstance;

  public Estudante(BigInteger matricula, String nome) {
    this.matricula = matricula;
    this.nome = nome;
    initCollatorInstance();
  }

  @Override
  public int compareTo(Estudante o) {
    int cmp = o.obterQtAvaliacoes() - this.obterQtAvaliacoes();
    if (cmp != 0)
      return cmp;

    return collatorInstance.compare(this.obterNome(), o.obterNome());
  }

  public void initCollatorInstance() {
    collatorInstance = Collator.getInstance(locale);
  }

  // Getters.
  public BigInteger obterRef() {
    return obterMatricula();
  }

  public BigInteger obterMatricula() {
    return matricula;
  }

  public String obterNome() {
    return nome.trim();
  }

  public int obterQtDisciplinas() {
    return disciplinas.size();
  }

  public int obterQtAvaliacoes() {
    return avaliacoes.size();
  }

  // Registro.
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
  public double obterMediaDeDisciplinasPorPeriodo() {
    if (periodos.size() != 0) {
      double resultado = (double) disciplinas.size() / (double) periodos.size();
      return Math.round(resultado * 10.0)/10.0;
    } else {
      return (double) 0;
    }
  }

  public double obterMediaDeAvaliacoesPorDisciplina() {
    if (obterQtDisciplinas() != 0) {
      double resultado = (double) obterQtAvaliacoes() / (double) obterQtDisciplinas();
      return Math.round(resultado * 10.0)/10.0;
    } else {
      return (double) 0;
    }
  }

  public double obterMontanteNotas() {
    double montante = 0;

    for (Atividade chave : avaliacoes.keySet()) {
      montante = montante + avaliacoes.get(chave).obterNota();
    }

    return montante;
  }

  public double obterMediaNotas() {
    if (obterQtAvaliacoes() != 0) {
      double resultado = obterMontanteNotas()/(double)obterQtAvaliacoes();
      return Math.round(resultado * 10.0)/10.0;
    } else {
      return (double) 0;
    }
  }

}
