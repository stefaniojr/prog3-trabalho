import java.util.*;

public class Atividade {
  String nome;
  String sincronismo;
  Disciplina disciplina;
  int numero;

  Map<Estudante, Avaliacao> avaliacoes = new HashMap<>();

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
    avaliacoes.put(estudante, new Avaliacao(estudante, nota));
  }

  public void obterAvaliacoes() {
    if (avaliacoes.size() == 0) {
      escrever.notFound("avaliacoes");
    } else {
      escrever.titleRelatorio("Avaliacoes");
      for (Estudante chave : avaliacoes.keySet()) {
        escrever.showSomething(chave.obterNome());
        escrever.showSomething(Float.toString(avaliacoes.get(chave).obterNota()));
        escrever.showAsterisks();
      }
    }
  }

}
