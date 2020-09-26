import java.util.*;

public class Disciplina {
  private String codigo;
  private String nome;
  private Periodo periodo;
  private Docente docente;

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
      System.out.println("Disciplina sem estudantes matriculados! :(\n");
    } else {
      System.out.println(this.obterNome() + " possui os seguintes estudantes matriculados:");
      for (Estudante estudante : estudantes) {
        System.out.println(" - " + estudante.obterNome());
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
      System.out.println("Nao ha atividades cadastradas para essa disciplina!\n");
    } else {
      System.out.println(this.obterNome() + " tem as seguintes atividades:");
      for (Atividade atividade : atividades) {
        System.out.println("Codigo: " + atividade.numero + " | Atividade: " + atividade.nome + " | Sicronismo: " + atividade.sincronismo);
      }
      System.out.print("\n");
    }
  }
  
}
