import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Desserializar {
    public Main iniciarDesserializacao(String arquivoSerializacao) throws IOException, ClassNotFoundException {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoSerializacao))){
            return (Main) in.readObject(); 
            
        } 
    }
}
