package com.github.stefaniojr.prog3.project.domain;

import java.io.Serializable;
import java.util.*;
import java.math.BigInteger;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.Comparable;
import com.github.stefaniojr.prog3.project.domain.atividades.*;

public class Disciplina implements Serializable, Comparable<Disciplina> {
  private static final long serialVersionUID = 1348633635465464580L;
  private String codigo;
  private String nome;
  private Periodo periodo;
  private Docente docente;

  private double montanteAvaliacoesEmAtividades = 0;
  private int montanteAvaliadoresEmAtividades = 0;

  private int cargaHoraria = 0;

  // Disciplina possui um HashMap com referências para os estudantes matriculados
  // nela.
  Map<BigInteger, Estudante> estudantes = new HashMap<>();
  // Disciplina possui um HashMap com referências para as atividades pertences a
  // ela, em que Integer representa o número sequencial da mesma.
  Map<Integer, Atividade> atividades = new HashMap<>();
  // Disciplina possui um ArrayList de Strings contendo as datas de avaliações.
  // List<String> dataAvaliacoes = new ArrayList<>();
  SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

  // Uma atividade começa com 1.
  int numeroAtividade = 1;

  Locale locale = new Locale("pt", "BR");
  private transient Collator collatorInstance;

  public Disciplina(String codigo, String nome, Periodo periodo, Docente docente) {
    this.codigo = codigo;
    this.nome = nome;
    this.periodo = periodo;
    this.docente = docente;
    initCollatorInstance();
  }

  @Override
  public int compareTo(Disciplina o) {
    return collatorInstance.compare(this.obterNome(), o.obterNome());
  }

  public void initCollatorInstance() {
    collatorInstance = Collator.getInstance(locale);
  }

  // Getters.
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

  public int obterNumeroDeAtividades() {
    return this.numeroAtividade - 1;
  }

  public int obterMontanteAvaliadoresEmAtividades() {
    return this.montanteAvaliadoresEmAtividades;
  }

  public double obterMontanteAvaliacoesEmAtividades() {
    return this.montanteAvaliacoesEmAtividades;
  }

  public int obterNumeroDeAlunosMatriculados() {
    return estudantes.size();
  }

  // Registro.
  public void adicionarEstudante(Estudante estudante) {
    estudantes.put(estudante.obterRef(), estudante);
  }

  public void adicionarAula(String nome, String sincronismo, Disciplina disciplina, String data, char tipo)
      throws ParseException {

    atividades.put(this.numeroAtividade, new Aula(nome, sincronismo, this, this.numeroAtividade, tipo, data));
    this.numeroAtividade = this.numeroAtividade + 1;

  }

  public void adicionarEstudo(String nome, String sincronismo, Disciplina disciplina, String conteudo, char tipo)
      throws ParseException {

    atividades.put(this.numeroAtividade, new Estudo(nome, sincronismo, this, this.numeroAtividade, conteudo, tipo));
    this.numeroAtividade = this.numeroAtividade + 1;

  }

  public void adicionarTrabalho(String nome, String sincronismo, Disciplina disciplina, String prazo,
      String nIntegrantesStr, String cargaHorariaStr, char tipo) throws ParseException {
    int nIntegrantes = 0;
    int cargaHoraria = 0;
    int nAux = 0;

    try {
      nAux = Integer.parseInt(nIntegrantesStr);
      nIntegrantes = nAux;
      nAux = Integer.parseInt(cargaHorariaStr);
      cargaHoraria = nAux;
      atividades.put(this.numeroAtividade,
          new Trabalho(nome, sincronismo, this, this.numeroAtividade, prazo, nIntegrantes, cargaHoraria, tipo));
      this.numeroAtividade = this.numeroAtividade + 1;
    } catch (NumberFormatException e) {
      System.out.println("Dado inválido: " + nAux + ".");
      nAux = 0;
    }

  }

  public void adicionarProva(String nome, String sincronismo, Disciplina disciplina, String data, String hora,
      String conteudo, char tipo) throws ParseException {

    atividades.put(this.numeroAtividade,
        new Prova(nome, sincronismo, this, this.numeroAtividade, data, hora, conteudo, tipo));
    this.numeroAtividade = this.numeroAtividade + 1;

  }

  // public void adicionarDataAvaliacao(String data) throws ParseException{
  // Collections.sort(dataAvaliacoes, new Comparator<Date>() {
  // public int compare(Date o1, Date o2) {
  // return o1.getDateTime().compareTo(o2.getDateTime());
  // }
  // });
  // dataAvaliacoes.add(df.parse(data));
  // }

  // Estatísticas.
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
    double montanteNotas = 0;
    int montanteAvaliadores = 0;

    for (Integer chave : atividades.keySet()) {
      Atividade atividade = atividades.get(chave);
      // Para uma atividade específica pegamos o montante de notas atribuídas a ela
      // por alunos.
      montanteNotas = montanteNotas + atividade.obterMontanteNotasAvaliacoes();
      // Para uma atividade específica pegamos o montante de avaliadores (alunos).
      montanteAvaliadores = montanteAvaliadores + atividade.obterQtAvaliadores();
    }
    // Com essas informações, podemos atribuir essas variáveis aos atributos de
    // classe relacionados ao cálculo de estatísticas.
    this.montanteAvaliacoesEmAtividades = montanteNotas;
    this.montanteAvaliadoresEmAtividades = montanteAvaliadores;

  }

  // Extras.
  public String obterDataAvaliacoes() {
    List<Date> data = new ArrayList<Date>();
    for (Integer chave : atividades.keySet()) {
      Atividade atividade = atividades.get(chave);
      data.addAll(atividade.obterDataAvaliacoes());
    }
    Collections.sort(data);
    List<String> dataStr = new ArrayList<String>();

    for (Date d : data)
      dataStr.add(df.format(d));

    return dataStr.toString().replace(",", "").replace("[", "").replace("]", "");
  }

  public Atividade obterAtividade(int numeroAtividade) {
    return atividades.get(numeroAtividade);
  }

  public Map<BigInteger, Estudante> obterEstudantes() {
    return estudantes;
  }

  public boolean jaMatriculado(BigInteger matricula) {
    return estudantes.containsKey(matricula);
  }

}
