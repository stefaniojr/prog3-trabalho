public class Periodo {
  private int ano;
  private char semestre;

  public Periodo(int anoRecebido, char semestreRecebido){
    this.ano = anoRecebido;
    this.semestre = semestreRecebido;
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
