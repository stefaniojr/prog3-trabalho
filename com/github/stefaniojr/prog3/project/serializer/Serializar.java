package com.github.stefaniojr.prog3.project.serializer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;
import com.github.stefaniojr.prog3.project.Main;

public class Serializar {
    
    private File arquivoSerializacao;

    public Serializar (File arquivoSerializacao){
        this.arquivoSerializacao = arquivoSerializacao;
    }

    public void iniciarSerializacao(Main aplicacao) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.arquivoSerializacao))) {
            out.writeObject(aplicacao);
            out.close();
        }
    }
}
