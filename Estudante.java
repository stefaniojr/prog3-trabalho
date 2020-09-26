import java.util.*;

public class Estudante {
  private int matricula;
  private String nome;

  ArrayList<Disciplina> disciplinas = new ArrayList<>();

  public Estudante(int matricula, String nome){
    this.matricula = matricula;
    this.nome = nome;
  }

  public int obterRef(){
    return obterMatricula();
  }

  public int obterMatricula(){
    return matricula;
  }

  public String obterNome(){
    return nome;
  }

  public void adicionarDisciplina(Disciplina disciplina){
    disciplinas.add(disciplina);
  }

  public void exibirDisciplinas(){
    if (disciplinas.size() == 0) {
      System.out.println("Nao ha disciplinas cadastradas para esse estudante!\n");
    } else {
      System.out.println(this.obterNome() + " esta cadastrado nas seguintes disciplinas:");
      for (Disciplina disciplina : disciplinas) {
        System.out.println(disciplina.obterRef() + " | Disciplina: " + disciplina.obterNome() + " | Docente responsavel: " + disciplina.obterDocente().obterNome());
      }
    }
  }

}
