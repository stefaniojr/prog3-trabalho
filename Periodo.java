import java.util.*;

public class Periodo {
  private int ano;
  private char semestre;

  Map<String, Disciplina> disciplinas = new HashMap<>();

  public Periodo(int ano, char semestre){
    this.ano = ano;
    this.semestre = semestre;
  }

  public String obterRef(){
    return obterAno() + "/" + obterSemestre();
  }

  public int obterAno(){
    return ano;
  }

  public char obterSemestre(){
    return semestre;
  }

  public void adicionarDisciplina(Disciplina disciplina){
    disciplinas.put(disciplina.obterRef(), disciplina);
  }

}
