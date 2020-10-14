import java.io.Serializable;
import java.util.*;

public class Periodo implements Serializable {

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

  public Map<String, Disciplina> obterDisciplinas (){
    return disciplinas;
  }

  public char obterSemestre(){
    return semestre;
  }

  public void adicionarDisciplina(Disciplina disciplina){
    disciplinas.put(disciplina.obterRef(), disciplina);
  }

}
