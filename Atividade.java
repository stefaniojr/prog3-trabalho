import java.util.*;

public class Atividade {
    String nome;
    String sincronismo;
    String disciplinaRef;
    int numero;

    ArrayList<Avaliacao> avaliacoes = new ArrayList<>();

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
          System.out.println("Essa atividade ainda nao foi avaliada! :(\n");
        } else {
          System.out.println("Atividade de codigo " + this.obterRef() + " tem as seguintes avaliacoes:");
          for (Avaliacao avaliacao : avaliacoes) {
            System.out.println("- Estudante " + avaliacao.obterAvaliador().obterNome() + " avaliaou essa atividade com nota " + avaliacao.obterNota() + ".");
          }
          System.out.print("\n");
        }
      }


}
