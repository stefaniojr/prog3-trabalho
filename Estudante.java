import java.util.*;

public class Estudante {
  private int matricula;
  private String nome;

  ArrayList<String> disciplinas = new ArrayList<>();

  public Estudante(int matricula, String nome){
    this.matricula = matricula;
    this.nome = nome;
  }

  public int obterRef(){
    return obterMatriculaEstudante();
  }

  public int obterMatriculaEstudante(){
    return matricula;
  }

  public String obterNomeEstudante(){
    return nome;
  }

  public void adicionarDisciplina(String disciplina){
    disciplinas.add(disciplina);
  }

  public void exibirDisciplinasDoEstudante(){
    for (int i=0; i < disciplinas.size(); i++){
      System.out.println("- " + disciplinas.get(i));
    }

    if (disciplinas.size() == 0){
      System.out.println("Estudante nao possui matricula em nenhuma disciplina! :(\n");
    }
  }

}
