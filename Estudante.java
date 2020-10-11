import java.util.*;

public class Estudante {
  private int matricula;
  private String nome;

  Map<String, Disciplina> disciplinas = new HashMap<>();
  Escrita escrever = new Escrita();
  Info info = new Info();

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

    info.disciplinasCadastradas(escrever, disciplinas);
    
  }

}
