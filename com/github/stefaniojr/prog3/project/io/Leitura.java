package com.github.stefaniojr.prog3.project.io;

import java.util.*;

public class Leitura {
    
    Scanner in; 

    public void iniciarLeitura(){
        this.in = new Scanner(System.in);
        in.useLocale(Locale.US);
    }

    public Scanner leitor(){
        return in;
    }

    public int inteiro(){
       return this.in.nextInt();
    }

    public float flutuante(){
        return this.in.nextFloat();
    }

    public String cadeiaCaract(){
        return this.in.nextLine();
    }

    public char caract(){
        return this.in.next().charAt(0);
    }

    public String linha(){
        return this.in.nextLine();
    }

    public void finalizarLeitura(){
        this.in.close();
    }

}
