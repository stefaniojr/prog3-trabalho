public class Docente {
  private String login;
  private String nome;
  private String site;

  public Docente(String login, String nome, String site){
    this.login = login;
    this.nome = nome;
    this.site = site;
  }

  public String obterRefDocente(){
    return obterLoginDocente();
  }

  public String obterLoginDocente(){
    return login;
  }

  public String obterNomeDocente(){
    return nome;
  }

  public String obterSiteDocente(){
    return site;
  }

}
