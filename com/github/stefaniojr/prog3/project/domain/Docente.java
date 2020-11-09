package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;

public class Docente implements Serializable {

  private String login;
  private String nome;
  private String site;

  float mediaAtividadesPorDisciplina = 0F;
  float percentualAtividadesSincronas = 0F;
  float percentualAtividadesAssincronas = 0F;
  float mediaAvaliacoes = 0F;

  Map<String, Disciplina> disciplinas = new HashMap<>();
  Map<String, Periodo> periodos = new HashMap<>();


  public Docente(String login, String nome, String site){
    this.login = login;
    this.nome = nome;
    this.site = site;
  }

  public String obterRef(){
    return obterLogin();
  }

  public String obterLogin(){
    return this.login;
  }

  public String obterEmail(){
    return this.login + "@ufes.br";
  }

  public String obterNome(){
    return this.nome;
  }

  public String obterSite(){
    return this.site;
  }

  public int obterNumeroDeDisciplinas(){
    return disciplinas.size();
  }

  public int obterNumeroDePeriodos(){
    return periodos.size();
  }

  public float obterMediaAtividadesPorDisciplina(){
    return this.mediaAtividadesPorDisciplina;
  }

  public int obterPercentualAtividadesSincronas(){
    return Math.round(this.percentualAtividadesSincronas);
  }

  public int obterPercentualAtividadesAssincronas(){
    return Math.round(this.percentualAtividadesAssincronas);
  }

  public float obterMediaAvaliacoes(){
    return this.mediaAvaliacoes;
  }

  public Map<String, Disciplina> obterDisciplinas(){
    return this.disciplinas;
  }

  public void calcularEstatisticasDeDocente(){
    int montanteAtividades = 0;
    int montanteAtividadesSincronas = 0;
    int montanteAtividadesAssincronas = 0;
    float montanteAvaliacoes = 0F;
    int montanteAvaliadores = 0;

    for (String chave : disciplinas.keySet()) {
      Disciplina disciplina = disciplinas.get(chave);
      montanteAtividades = montanteAtividades + disciplina.obterNumeroDeAtividades();
      montanteAtividadesSincronas = montanteAtividadesSincronas + disciplina.obterNumeroAtividadesSincronas();
      montanteAtividadesAssincronas = montanteAtividadesAssincronas + disciplina.obterNumeroAtividadesAssincronas();
      disciplina.calcularEstatisticasAtividadesDeDisciplina();
      montanteAvaliacoes = montanteAvaliacoes + disciplina.obterMontanteAvaliacoesEmAtividades();
      montanteAvaliadores = montanteAvaliadores + disciplina.obterMontanteAvaliadoresEmAtividades();
    }
    if(obterNumeroDeDisciplinas()!=0)
      this.mediaAtividadesPorDisciplina = (float)montanteAtividades/(float)obterNumeroDeDisciplinas();
    if(montanteAtividades!=0){
      this.percentualAtividadesSincronas = ((float)montanteAtividadesSincronas/(float)montanteAtividades)*100;
      this.percentualAtividadesAssincronas = ((float)montanteAtividadesAssincronas/(float)montanteAtividades)*100;
    }
      
    if(montanteAvaliadores!=0)
      this.mediaAvaliacoes = montanteAvaliacoes/(float)montanteAvaliadores;
    
  }

  public void adicionarDisciplina(Disciplina disciplina){
    disciplinas.put(disciplina.obterRef(), disciplina);
  }

  public void adicionarPeriodo(Periodo periodo){
    periodos.put(periodo.obterRef(), periodo);
  }

}
