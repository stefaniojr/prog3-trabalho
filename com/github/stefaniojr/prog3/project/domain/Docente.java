package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;

public class Docente implements Serializable {

  private String login;
  private String nome;
  private String site;

  // Atributos para auxiliar no cálculo das estatísticas.
  private float mediaAtividadesPorDisciplina = 0F;
  private float percentualAtividadesSincronas = 0F;
  private float percentualAtividadesAssincronas = 0F;
  private float mediaAvaliacoes = 0F;

  // Docente possui um HashMap com referências para os períodos em que ele
  // leciona/lecionou disciplinas.
  Map<String, Periodo> periodos = new HashMap<>();
  // Docente possui um HashMap com referências para as disciplinas que ele
  // leciona/lecionou.
  Map<String, Disciplina> disciplinas = new HashMap<>();

  public Docente(String login, String nome, String site) {
    this.login = login;
    this.nome = nome;
    this.site = site;
  }

  // Getters.
  public String obterRef() {
    return obterLogin();
  }

  public String obterLogin() {
    return this.login;
  }

  public String obterEmail() {
    return this.login + "@ufes.br";
  }

  public String obterNome() {
    return this.nome;
  }

  public String obterSite() {
    return this.site;
  }

  // Registro.
  public void adicionarDisciplina(Disciplina disciplina) {
    disciplinas.put(disciplina.obterRef(), disciplina);
  }

  public void adicionarPeriodo(Periodo periodo) {
    periodos.put(periodo.obterRef(), periodo);
  }

  // Estatísticas.
  public float obterMediaAtividadesPorDisciplina() {
    return this.mediaAtividadesPorDisciplina;
  }

  public int obterPercentualAtividadesSincronas() {
    return Math.round(this.percentualAtividadesSincronas);
  }

  public int obterPercentualAtividadesAssincronas() {
    return Math.round(this.percentualAtividadesAssincronas);
  }

  public float obterMediaAvaliacoes() {
    return this.mediaAvaliacoes;
  }

  public void calcularEstatisticasDeDocente() {
    int montanteAtividades = 0;
    int montanteAtividadesSincronas = 0;
    int montanteAtividadesAssincronas = 0;
    float montanteAvaliacoes = 0F;
    int montanteAvaliadores = 0;

    // Algumas estatísticas precisam de informações essenciais de disciplinas, logo,
    // iteramos o HashMap e calculamos utilizando métodos intrínsecos à classe
    // Disciplina. Note que esse método existe no contexto de um disciplina, pois
    // estatísticas mais elaboradas são exigidas.
    for (String chave : disciplinas.keySet()) {
      Disciplina disciplina = disciplinas.get(chave);
      montanteAtividades = montanteAtividades + disciplina.obterNumeroDeAtividades();
      montanteAtividadesSincronas = montanteAtividadesSincronas + disciplina.obterNumeroAtividadesSincronas();
      montanteAtividadesAssincronas = montanteAtividadesAssincronas + disciplina.obterNumeroAtividadesAssincronas();
      disciplina.calcularEstatisticasAtividadesDeDisciplina();
      montanteAvaliacoes = montanteAvaliacoes + disciplina.obterMontanteAvaliacoesEmAtividades();
      montanteAvaliadores = montanteAvaliadores + disciplina.obterMontanteAvaliadoresEmAtividades();
    }

    // Calcula o valor dos atributos de classe relacionados aos cálculos de
    // estatísticas.
    if (obterNumeroDeDisciplinas() != 0)
      this.mediaAtividadesPorDisciplina = (float) montanteAtividades / (float) obterNumeroDeDisciplinas();
    if (montanteAtividades != 0) {
      this.percentualAtividadesSincronas = ((float) montanteAtividadesSincronas / (float) montanteAtividades) * 100;
      this.percentualAtividadesAssincronas = ((float) montanteAtividadesAssincronas / (float) montanteAtividades) * 100;
    }

    if (montanteAvaliadores != 0)
      this.mediaAvaliacoes = montanteAvaliacoes / (float) montanteAvaliadores;

  }

  // Extras.
  public Map<String, Disciplina> obterDisciplinas() {
    return this.disciplinas;
  }

  public int obterNumeroDeDisciplinas() {
    return disciplinas.size();
  }

  public int obterNumeroDePeriodos() {
    return periodos.size();
  }
}
