public class Disciplina {
  private String codigo;
  private String nome;
  private String periodo;
  private String docenteResponsavel;

  public Disciplina (String codigoRecebido, String nomeRecebido, String periodoRecebido, String docenteResponsavelRecebido){
    this.codigo = codigoRecebido;
    this.nome = nomeRecebido;
    this.periodo = periodoRecebido;
    this.docenteResponsavel = docenteResponsavelRecebido;
  }

  public String obterRefDisciplina(){
    return codigo + "-" + periodo;
  }
  
  public String obterCodigoDisciplina() {
    return codigo;
  }

  public String obterNomeDisciplina() {
    return nome;
  }

  public String obterPeriodoDisciplina() {
    return periodo;
  }

  public String obterDocenteResponsavelDisciplina(){
    return docenteResponsavel;
  }

  

}
