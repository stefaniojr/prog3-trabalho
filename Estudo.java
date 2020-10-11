import java.util.*;

public class Estudo extends Atividade {

    Map<String, String> conteudos = new HashMap<>();

    public Estudo(String nome, String sincronismo, Disciplina disciplina, int numero, Map<String, String> conteudos) {
        super(nome, sincronismo, disciplina, numero);

        this.conteudos = conteudos;
    }
}
