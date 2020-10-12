import java.io.Serializable;
import java.util.*;

public class Docente implements Serializable {
  
  private String login;
  private String nome;
  private String site;


  float mediaAtividadesPorDisciplina = 0;
  float percentualAtividadesSincronas = 0;
  float mediaAvaliacoes = 0;

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

  public float obterPercentualAtividadesSincronas(){
    return this.percentualAtividadesSincronas;
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
    float montanteAvaliacoes = 0;
    int montanteAvaliadores = 0;
    
    for (String chave : disciplinas.keySet()) {
      montanteAtividades = montanteAtividades + disciplinas.get(chave).obterNumeroDeAtividades();
      montanteAtividadesSincronas = montanteAtividadesSincronas + disciplinas.get(chave).obterNumeroAtividadesSincronas();
      disciplinas.get(chave).calcularEstatisticasAtividadesDeDisciplina();
      montanteAvaliacoes = montanteAvaliacoes + disciplinas.get(chave).obterMontanteAvaliacoesEmAtividades();
      montanteAvaliadores = montanteAvaliadores + disciplinas.get(chave).obterMontanteAvaliadoresEmAtividades();
    }

    this.mediaAtividadesPorDisciplina = montanteAtividades/obterNumeroDeDisciplinas();
    this.percentualAtividadesSincronas = montanteAtividadesSincronas/montanteAtividades;
    this.mediaAvaliacoes = montanteAvaliacoes/montanteAvaliadores;
  }

  public void adicionarDisciplina(Disciplina disciplina){
    disciplinas.put(disciplina.obterRef(), disciplina);
  }

  public void adicionarPeriodo(Periodo periodo){
    periodos.put(periodo.obterRef(), periodo);
  }

}
