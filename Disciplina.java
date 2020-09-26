import java.util.*;

public class Disciplina {
  private String codigo;
  private String nome;
  private String periodo;
  private String docenteResponsavel;

  ArrayList<Integer> alunos = new ArrayList<>();

  public Disciplina (String codigo, String nome, String periodo, String docenteResponsavel){
    this.codigo = codigo;
    this.nome = nome;
    this.periodo = periodo;
    this.docenteResponsavel = docenteResponsavel;
  }

  public String obterRefDisciplina(){
    return codigo + "-" + periodo;
  }
  
  public String obterCodigoDisciplina() {
    return codigo;
  }

  public String obterNomeDisciplina() {
    return nome;
  }

  public String obterPeriodoDisciplina() {
    return periodo;
  }

  public String obterDocenteResponsavelDisciplina(){
    return docenteResponsavel;
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

}
