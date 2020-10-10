import java.util.*;

public class Atividade {
  String nome;
  String sincronismo;
  Disciplina disciplina;
  int numero;

  List<Avaliacao> avaliacoes = new ArrayList<>();
  Relatorio relatorio = new Relatorio();

  Escrita escrever = new Escrita();

  public Atividade(String nome, String sincronismo, Disciplina disciplina, int numero) {
    this.nome = nome;
    this.sincronismo = sincronismo;
    this.disciplina = disciplina;
    this.numero = numero;
  }

  public int obterRef() {
    return numero;
  }

  public String obterNome(){
    return this.nome;
  }

  public String obterSincronismo(){
    return this.sincronismo;
  }

  public Disciplina obterDisciplina(){
    return this.disciplina;
  }

  public void avaliarAtividade(Estudante estudante, float nota) {
    avaliacoes.add(new Avaliacao(estudante, nota));;
  }

  public void exibirAvaliacoes() {
    relatorio.avaliacoesCadastradas(escrever, avaliacoes);
  }

}
