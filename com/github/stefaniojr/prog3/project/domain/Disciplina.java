package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;
import java.math.BigInteger;
import com.github.stefaniojr.prog3.project.domain.atividades.*;

public class Disciplina implements Serializable {

  private String codigo;
  private String nome;
  private Periodo periodo;
  private Docente docente;

  float montanteAvaliacoesEmAtividades = 0;
  int montanteAvaliadoresEmAtividades = 0;

  int cargaHoraria = 0;

  Map<BigInteger, Estudante> estudantes = new HashMap<>();
  Map<Integer, Atividade> atividades = new HashMap<>();
  ArrayList<String> dataAvaliacoes = new ArrayList<>();

  int numeroAtividade = 1;

  public Disciplina(String codigo, String nome, Periodo periodo, Docente docente) {
    this.codigo = codigo;
    this.nome = nome;
    this.periodo = periodo;
    this.docente = docente;
  }

  public String obterRef() {
    return codigo + "-" + periodo.obterRef();
  }

  public String obterCodigo() {
    return codigo;
  }

  public String obterNome() {
    return nome;
  }

  public Periodo obterPeriodo() {
    return periodo;
  }

  public Docente obterDocente() {
    return docente;
  }

  public String dataAvaliacoes() {
    dataAvaliacoes.removeIf(Objects::isNull);
    return dataAvaliacoes.toString().replace(",", "").replace("[", "").replace("]", "");
  }

  public Map<BigInteger, Estudante> obterEstudantes() {
    return estudantes;
  }

  public float obterMontanteAvaliacoesEmAtividades() {
    return this.montanteAvaliacoesEmAtividades;
  }

  public int obterMontanteAvaliadoresEmAtividades() {
    return this.montanteAvaliadoresEmAtividades;
  }

  public int obterNumeroDeAtividades() {
    return numeroAtividade - 1;
  }

  public int obterNumeroDeAlunosMatriculados() {
    return estudantes.size();
  }

  public int obterNumeroAtividadesSincronas() {
    int nAtividadesSincronas = 0;
    for (Integer chave : atividades.keySet())
      if (atividades.get(chave).obterSincronismo().equals("sincrona"))
        nAtividadesSincronas++;
    return nAtividadesSincronas;
  }

  public int obterNumeroAtividadesAssincronas() {
    int nAtividadesAssincronas = 0;
    for (Integer chave : atividades.keySet())
      if (atividades.get(chave).obterSincronismo().equals("assincrona"))
        nAtividadesAssincronas++;
    return nAtividadesAssincronas;
  }

  public Map<Integer, Atividade> obterAtividadesAvaliativas() {
    Map<Integer, Atividade> atividadesAvaliativas = new HashMap<>();

    for (Integer chave : atividades.keySet()) {
      Atividade atividade = atividades.get(chave);
      if (atividade.isAvaliativa()) {
        atividadesAvaliativas.put(atividade.obterNumeroSequencial(), atividade);
      }
    }

    return atividadesAvaliativas;
  }

  public int obterPercentualAtividadesSincronas() {
    if (obterNumeroDeAtividades() != 0) {
      return Math.round(((float) obterNumeroAtividadesSincronas() / (float) obterNumeroDeAtividades()) * 100);
    } else {
      return 0;
    }
  }

  public int obterPercentualAtividadesAssincronas() {
    if (obterNumeroDeAtividades() != 0) {
      return Math.round(((float) obterNumeroAtividadesAssincronas() / (float) obterNumeroDeAtividades()) * 100);
    } else {
      return 0;
    }
  }

  public int obterCargaHorariaDisciplina() {

    for (Integer chave : atividades.keySet()) {
      this.cargaHoraria = this.cargaHoraria + atividades.get(chave).obterCargaHoraria();
    }
    return this.cargaHoraria;
  }

  public void calcularEstatisticasAtividadesDeDisciplina() {
    float montanteNotas = 0;
    int montanteAvaliadores = 0;

    for (Integer chave : atividades.keySet()) {
      Atividade atividade = atividades.get(chave);
      montanteNotas = montanteNotas + atividade.obterMontanteNotasAvaliacoes();
      montanteAvaliadores = montanteAvaliadores + atividade.obterQtAvaliadores();
    }

    this.montanteAvaliacoesEmAtividades = montanteNotas;
    this.montanteAvaliadoresEmAtividades = montanteAvaliadores;

  }

  public Atividade obterAtividade(int numeroAtividade) {
    return atividades.get(numeroAtividade);
  }

  public void adicionarEstudante(Estudante estudante) {
    estudantes.put(estudante.obterRef(), estudante);
  }

  public void adicionarAula(String nome, String sincronismo, Disciplina disciplina, String data) {

    atividades.put(this.numeroAtividade, new Aula(nome, sincronismo, this, this.numeroAtividade, data));
    this.numeroAtividade = this.numeroAtividade + 1;

  }

  public void adicionarEstudo(String nome, String sincronismo, Disciplina disciplina, String conteudo) {

    atividades.put(this.numeroAtividade, new Estudo(nome, sincronismo, this, this.numeroAtividade, conteudo));
    this.numeroAtividade = this.numeroAtividade + 1;

  }

  public void adicionarTrabalho(String nome, String sincronismo, Disciplina disciplina, String prazo,
      String nIntegrantesStr, String cargaHorariaStr) {
    int nIntegrantes = 0;
    int cargaHoraria = 0;
    int nAux = 0;

    try {
      nAux = Integer.parseInt(nIntegrantesStr);
      nIntegrantes = nAux;
      nAux = Integer.parseInt(cargaHorariaStr);
      cargaHoraria = nAux;
      atividades.put(this.numeroAtividade,
          new Trabalho(nome, sincronismo, this, this.numeroAtividade, prazo, nIntegrantes, cargaHoraria));
      this.numeroAtividade = this.numeroAtividade + 1;
    } catch (NumberFormatException e) {
      System.out.println("Dado invalido: " + nAux);
      nAux = 0;
    }

  }

  // Setters

  public void adicionarProva(String nome, String sincronismo, Disciplina disciplina, String data, String hora,
      String conteudo) {

    atividades.put(this.numeroAtividade,
        new Prova(nome, sincronismo, this, this.numeroAtividade, data, hora, conteudo));
    this.numeroAtividade = this.numeroAtividade + 1;

  }

  public void adicionarDataAvaliacao(String data) {
    dataAvaliacoes.add(data);
  }

  public boolean jaMatriculado(BigInteger matricula) {
    return estudantes.containsKey(matricula);
  }

}
