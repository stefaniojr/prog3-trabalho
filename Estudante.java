import java.util.*;

public class Estudante {
  private int matricula;
  private String nome;

  ArrayList<Disciplina> disciplinas = new ArrayList<>();
  Escrita escrever = new Escrita();

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
      escrever.naoHa("disciplinas");
    } else {
      escrever.cadastrados("Disciplinas");
      for (Disciplina disciplina : disciplinas) {
        escrever.disciplinaCadastrada(disciplina.obterRef(), disciplina.obterNome(), disciplina.obterDocente().obterNome());
      }
    }
  }

}
