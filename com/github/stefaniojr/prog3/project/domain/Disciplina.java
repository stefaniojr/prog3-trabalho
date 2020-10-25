package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;
import com.github.stefaniojr.prog3.project.io.Escrita;
import com.github.stefaniojr.prog3.project.domain.atividades.*;

public class Disciplina implements Serializable {

  private String codigo;
  private String nome;
  private Periodo periodo;
  private Docente docente;

  float montanteAvaliacoesEmAtividades = 0;
  int montanteAvaliadoresEmAtividades = 0;

  int cargaHoraria = 0;

  Escrita escrever = new Escrita();

  Map<Integer, Estudante> estudantes = new HashMap<>();
  Map<Integer, Atividade> atividades = new HashMap<>();

  int numeroAtividade = 1;

  public Disciplina(String codigo, String nome, Periodo periodo, Docente docente){
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

  public float obterMontanteAvaliacoesEmAtividades(){
    return this.montanteAvaliacoesEmAtividades;
  }

  public int obterMontanteAvaliadoresEmAtividades(){
    return this.montanteAvaliadoresEmAtividades;
  }

  public int obterNumeroDeAtividades(){
    return numeroAtividade - 1;
  }

  public int obterNumeroDeAlunosMatriculados(){
    return estudantes.size();
  }

  public int obterNumeroAtividadesSincronas(){
    int nAtividadesSincronas = 0;
    for (Integer chave: atividades.keySet()){
      if (atividades.get(chave).obterSincronismo().equals("sincrona")){
        nAtividadesSincronas++;
      }
    }

    return nAtividadesSincronas;
  }

  public Map<Integer, Atividade> obterAtividadesAvaliativas(){
    Map<Integer, Atividade> atividadesAvaliativas = new HashMap<>();

    for (Integer chave: atividades.keySet()){
      Atividade atividade = atividades.get(chave);
      if(atividade.isAvaliativa()){
        atividadesAvaliativas.put(atividade.obterNumeroSequencial(), atividade);
        }
      }

    return atividadesAvaliativas;
  }

  public float obterPercentualAtividadesSincronas(){
    if(obterNumeroDeAtividades()!=0){
      return obterNumeroAtividadesSincronas()/obterNumeroDeAtividades();
    }
    else{
      return 0;
    }
  }

  public int obterCargaHorariaDisciplina(){

    for (Integer chave: atividades.keySet()){
        this.cargaHoraria = this.cargaHoraria + atividades.get(chave).obterCargaHoraria();
    }
    return this.cargaHoraria;
  }

  public void calcularEstatisticasAtividadesDeDisciplina(){
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

  public void adicionarEstudo(String nome, String sincronismo, Disciplina disciplina, Map<String, String> conteudos) {

    atividades.put(this.numeroAtividade, new Estudo(nome, sincronismo, this, this.numeroAtividade, conteudos));
    this.numeroAtividade = this.numeroAtividade + 1;

  }

  public void adicionarTrabalho(String nome, String sincronismo, Disciplina disciplina, String prazo, int nIntegrantes, int cargaHoraria) {

    atividades.put(this.numeroAtividade, new Trabalho(nome, sincronismo, this, this.numeroAtividade, prazo, nIntegrantes, cargaHoraria));
    this.numeroAtividade = this.numeroAtividade + 1;

  }

  public void adicionarProva(String nome, String sincronismo, Disciplina disciplina, String data, List<String> conteudos) {

    atividades.put(this.numeroAtividade, new Prova(nome, sincronismo, this, this.numeroAtividade, data, conteudos));
    this.numeroAtividade = this.numeroAtividade + 1;

  }

  // public void exibirAtividades() {
  //   info.atividadesCadastradas(escrever, atividades);
  // }

  // public void exibirEstudantes() {
  //   info.estudantesCadastrados(escrever, estudantes);
  // }



}
