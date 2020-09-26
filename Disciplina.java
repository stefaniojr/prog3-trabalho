import java.util.*;

public class Disciplina {
  private String codigo;
  private String nome;
  private Periodo periodo;
  private Docente docente;

  ArrayList<Integer> alunos = new ArrayList<>();
  ArrayList<Atividade> atividades = new ArrayList<>();
  int numeroAtividade = 1;

  public Disciplina (String codigo, String nome, Periodo periodo, Docente docente){
    this.codigo = codigo;
    this.nome = nome;
    this.periodo = periodo;
    this.docente = docente;
  }

  public String obterRef(){
    return codigo + "-" + periodo;
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

  public void receberAluno(int aluno){
    alunos.add(aluno);
  }

  public void exibirAlunos(){
    for (int i=0; i < alunos.size(); i++){
      System.out.println("- " + alunos.get(i));
    }

    if (alunos.size() == 0){
      System.out.println("Disciplina sem estudantes matriculados! :(\n");
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
}
