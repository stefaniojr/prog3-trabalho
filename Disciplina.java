import java.util.*;

public class Disciplina {
  private String codigo;
  private String nome;
  private Periodo periodo;
  private Docente docente;

  Escrita escrever = new Escrita();

  ArrayList<Estudante> estudantes = new ArrayList<>();
  ArrayList<Atividade> atividades = new ArrayList<>();
  int numeroAtividade = 1;

  public Disciplina (String codigo, String nome, Periodo periodo, Docente docente){
    this.codigo = codigo;
    this.nome = nome;
    this.periodo = periodo;
    this.docente = docente;
  }

  public String obterRef(){
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

  public Docente obterDocente(){
    return docente;
  }

  public void adicionarEstudante(Estudante estudante){
    estudantes.add(estudante);
  }

  public void exibirEstudantes(){
    if (estudantes.size() == 0) {
      escrever.naoHa("estudantes");
    } else {
      escrever.cadastrados("Estudantes");
      for (Estudante estudante : estudantes) {
        escrever.estudanteCadastrado(estudante.obterRef(), estudante.obterNome());
      }
    }
  }

  public boolean adicionarAtividade(String nome, String sincronismo){
    if (atividades.add(new Atividade(nome, sincronismo, this.obterRef() , this.numeroAtividade))){
      this.numeroAtividade = this.numeroAtividade + 1;
      return true;
    } else {
      return false;
    }
  }

  public void obterAtividades(){
    if (atividades.size() == 0) {
      escrever.naoHa("atividades");
    } else {
      escrever.cadastrados("Atividades");
      for (Atividade atividade : atividades) {
        escrever.atividadeCadastrada(atividade.numero, atividade.nome, atividade.sincronismo);
        atividade.obterAvaliacoes();
      }
    }
  }

  public Atividade obterAtividade(int numeroAtividade){
    for(Atividade atividade: atividades){
      if(atividade.obterRef() == numeroAtividade){
        return atividade;
      }
    }
    return null;
  }
  
}
