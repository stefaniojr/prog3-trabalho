package com.github.stefaniojr.prog3.project.domain.atividades;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.github.stefaniojr.prog3.project.domain.*;

public abstract class Atividade implements Serializable {
  private static final long serialVersionUID = 1348633635465464564L;
  private String nome;
  private String sincronismo;
  private Disciplina disciplina;
  private int numero;
  private int cargaHoraria;
  private boolean avaliativa;
  private char tipo;

  // Atividade possui uma lista de avaliações.
  List<Avaliacao> avaliacoes = new ArrayList<>();
  SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
  List<Date> data = new ArrayList<Date>();

  public Atividade(String nome, String sincronismo, Disciplina disciplina, int cargaHoraria, int numero,
      boolean avaliativa, char tipo, String data) throws ParseException {
    this.nome = nome;
    this.sincronismo = sincronismo;
    this.disciplina = disciplina;
    this.numero = numero;
    this.cargaHoraria = cargaHoraria;
    this.avaliativa = avaliativa;
    this.tipo = tipo;
    if (data != null)
      this.data.add(df.parse(data));
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

  public int obterNumeroSequencial() {
    return this.numero;
  }

  public Disciplina obterDisciplina() {
    return this.disciplina;
  }

  public char obterTipo() {
    return this.tipo;
  }

  public List<Date> obterDataAvaliacoes() {
    return this.data;
  }

  // Estatísticas.
  public double obterMontanteNotasAvaliacoes() {
    double montanteNotas = 0;
    for (Avaliacao avaliacao : avaliacoes) {
      montanteNotas = montanteNotas + avaliacao.obterNota();
    }
    return montanteNotas;
  }
  
  // Extras.

  public Avaliacao encontrarAvaliacao(Estudante estudante) {
    for (Avaliacao avaliacao : avaliacoes) {
      if (estudante.obterNome().equals(avaliacao.obterAvaliador().obterNome())) {
        return avaliacao;
      }
    }
    return null;
  }

  public boolean isAvaliativa() {
    return this.avaliativa;
  }

  public void avaliarAtividade(Estudante estudante, double nota) {
    avaliacoes.add(new Avaliacao(estudante, nota));
  }

}
