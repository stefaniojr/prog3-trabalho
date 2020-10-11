import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializar {
    public void iniciarSerializacao(String arquivoSerializacao, Main aplicacao) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivoSerializacao))) {
            out.writeObject(aplicacao);
            out.close();
        }
    }
}
