public class Estudante {
  public int matricula;
  public String nome;

  public Estudante(int matriculaRecebida, String nomeRecebido){
    this.matricula = matriculaRecebida;
    this.nome = nomeRecebido;
  }

  public int obterMatriculaEstudante(){
    return matricula;
  }

  public String obterNomeEstudante(){
    return nome;
  }

}
