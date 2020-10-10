import java.util.*;

public class Estudante {
  private int matricula;
  private String nome;

  Map<String, Disciplina> disciplinas = new HashMap<>();
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
    disciplinas.put(disciplina.obterRef(), disciplina);
  }

  public void exibirDisciplinas(){
    if (disciplinas.size() == 0) {
      escrever.notFound("disciplinas");
    } else {
      escrever.titleRelatorio("Disciplinas");
      for (String chave : disciplinas.keySet()) {
        escrever.showSomething(chave);
        escrever.showSomething(disciplinas.get(chave).obterNome());
        escrever.showSomething(disciplinas.get(chave).obterDocente().obterNome());
        escrever.showAsterisks();
      }
    }
  }

}
