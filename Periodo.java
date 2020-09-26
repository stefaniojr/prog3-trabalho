public class Periodo {
  private int ano;
  private char semestre;

  public Periodo(int ano, char semestre){
    this.ano = ano;
    this.semestre = semestre;
  }

  public String obterRefPeriodo(){
    return obterAno() + "/" + obterSemestre();
  }

  public int obterAno(){
    return ano;
  }

  public char obterSemestre(){
    return semestre;
  }

}
