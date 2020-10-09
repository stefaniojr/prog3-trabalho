import java.util.*;

public class Atividade {
    String nome;
    String sincronismo;
    String disciplinaRef;
    int numero;

    ArrayList<Avaliacao> avaliacoes = new ArrayList<>();

    Escrita escrever = new Escrita();

    public Atividade(String nome, String sincronismo, String disciplinaRef, int numero) {
        this.nome = nome;
        this.sincronismo = sincronismo;
        this.disciplinaRef = disciplinaRef;
        this.numero = numero;
    }

    public int obterRef() {
        return numero;
    }

    public void avaliarAtividade(Estudante estudante, float nota){
        avaliacoes.add(new Avaliacao(estudante, nota));
    }

    public void obterAvaliacoes(){
        if (avaliacoes.size() == 0) {
          escrever.naoHa("avaliacoes");
        } else {
          escrever.cadastrados("Avaliacoes");
          for (Avaliacao avaliacao : avaliacoes) {
            escrever.avaliacaoCadastrada(avaliacao.obterAvaliador().obterNome(), avaliacao.obterNota());
          }
          
        }
      }


}
