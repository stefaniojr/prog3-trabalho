public class Docente {
  private String login;
  private String nome;
  private String site;

  public Docente(String loginRecebido, String nomeRecebido, String siteRecebido){
    this.login = loginRecebido;
    this.nome = nomeRecebido;
    this.site = siteRecebido;
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
