import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Prova extends Atividade {
    
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
    Date data;

    List<String> conteudos = new ArrayList<>();

    public Prova(String nome, String sincronismo, Disciplina disciplina, int numero, String data, List<String> conteudos){
        super(nome, sincronismo, disciplina, 2, numero, true);
        
        try{
            this.data = df.parse(data);
        } catch (ParseException e){
            e.printStackTrace();
        }
        this.conteudos = conteudos;
        
    }
}
