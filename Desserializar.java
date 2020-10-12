import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;

public class Desserializar {

    private File arquivoSerializacao;

    public Desserializar (File arquivoSerializacao){
        this.arquivoSerializacao = arquivoSerializacao;
    }
    public Main iniciarDesserializacao() throws IOException, ClassNotFoundException {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoSerializacao))){
            return (Main) in.readObject(); 
        } 
    }
}
