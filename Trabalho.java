import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Trabalho extends Atividade {
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
    Date prazo;

    public Trabalho(String nome, String sincronismo, Disciplina disciplina, int numero, String prazo, int nIntegrantes, int cargaHoraria){
        super(nome, sincronismo, disciplina, numero);

        try{
            this.prazo = df.parse(prazo);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
