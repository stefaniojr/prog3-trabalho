import java.util.*;

public class Docente {
  private String login;
  private String nome;
  private String site;

  ArrayList<String> disciplinas = new ArrayList<>();

  public Docente(String login, String nome, String site){
    this.login = login;
    this.nome = nome;
    this.site = site;
  }

  public String obterRef(){
    return obterLogin();
  }

  public String obterLogin(){
    return login;
  }

  public String obterNome(){
    return nome;
  }

  public String obterSite(){
    return site;
  }

  public void adicionarDisciplina(String disciplina){
    disciplinas.add(disciplina);
  }

}
