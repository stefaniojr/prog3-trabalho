import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Aula extends Atividade {
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
    Date data;
    
    public Aula(String nome, String sincronismo, Disciplina disciplina, int numero, String data){
        super(nome, sincronismo, disciplina, 2, numero, false);

        try{
            this.data = df.parse(data);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
